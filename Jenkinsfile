pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-21'
            args "-v ${env.HOME}/.m2:/root/.m2"  // ✅ Fixed: proper host home dir + double quotes
        }
    }

    parameters {
        choice(name: 'REPORT_TYPE', choices: ['REPORT_PORTAL', 'EXTENT_REPORT'], description: 'reportType (-DreportType)')
        booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Run tests headless? (-Dheadless=true/false)')
    }

    options {
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '30'))
        timestamps()
    }

    environment {
        MAVEN_OPTS = '-B'
    }

    stages {
        stage('Prepare') {
            steps {
                script {
                    echo "Node: ${env.NODE_NAME}  Workspace: ${env.WORKSPACE}"
                    sh 'java -version || true'
                    sh 'mvn -v'
                }
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                script {
                    def report = (params.REPORT_TYPE ?: 'REPORT_PORTAL').trim()
                    def headless = params.HEADLESS ? 'true' : 'false'

                    def mvnCmd = "mvn clean test -Psmoke -DreportType=${report} -Dheadless=${headless}"

                    echo "Running: ${mvnCmd}"
                    sh "set -e; ${mvnCmd}"  // ✅ Fixed: string interpolation + fail on error
                }
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Archive') {
            steps {
                script {
                    archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true

                    def indexHtmlPath = 'index.html'

                    if (params.REPORT_TYPE == 'EXTENT_REPORT') {
                        if (fileExists(indexHtmlPath)) {
                            echo "Archiving extent report index: ${indexHtmlPath}"
                            archiveArtifacts artifacts: indexHtmlPath, allowEmptyArchive: false
                        } else {
                            echo "WARNING: Extent report index not found at ${indexHtmlPath} — skipping archive"
                        }
                    } else {
                        echo "REPORT_TYPE=${params.REPORT_TYPE} -> not archiving index.html"
                    }
                }
            }
        }
    }

    post {
        success {
            echo "Build succeeded: ${env.BRANCH_NAME} ${env.BUILD_URL}"
        }
        failure {
            echo "Build failed: ${env.BRANCH_NAME}"
        }
        always {