pipeline {
    agent any

    parameters {
        choice(
            name: 'REPORT_TYPE',
            choices: ['REPORT_PORTAL', 'EXTENT_REPORT'],
            description: 'Select report type: REPORT_PORTAL or EXTENT_REPORT'
        )
        booleanParam(
            name: 'HEADLESS',
            defaultValue: true,
            description: 'Run tests in headless mode? (true/false)'
        )
    }

    options {
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '20'))
        timestamps()
    }

    stages {
        stage('Prepare') {
            steps {
                script {
                    echo "Node: ${env.NODE_NAME}"
                    echo "Workspace: ${env.WORKSPACE}"
                    sh 'java -version || echo "Java not required on host"'
                    sh 'mvn -v || echo "Maven not required on host"'
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
                    def report = params.REPORT_TYPE.trim()
                    def headless = params.HEADLESS ? 'true' : 'false'

                    def mvnCmd = "mvn clean test -Psmoke -DreportType=${report} -Dheadless=${headless}"

                    echo "Executing: ${mvnCmd}"
                    sh "set -e; ${mvnCmd}"
                }
            }

            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }
    }

    post {
        success {
            echo "Build succeeded: ${env.BRANCH_NAME} | Report: ${params.REPORT_TYPE} | Headless: ${params.HEADLESS}"
        }
        failure {
            echo "Build failed: ${env.BRANCH_NAME}"
        }
        always {
            // Archive index.html only if it exists and REPORT_TYPE is EXTENT_REPORT
            script {
                def indexHtmlPath = 'target/artifacts/index.html'
                if (params.REPORT_TYPE == 'EXTENT_REPORT') {
                    if (fileExists(indexHtmlPath)) {
                        echo "Archiving Extent Report: ${indexHtmlPath}"
                        archiveArtifacts artifacts: 'target/artifacts/**', allowEmptyArchive: false
                        publishHTML([
                               target: [
                               reportDir: '.',          // Look in workspace root
                               reportFiles: indexHtmlPath,
                               reportName: 'Extent Report',
                               keepAll: true           // Keep all reports across builds
                               ]
                              ])
                    } else {
                        echo "WARNING: Extent Report index.html not found at ${indexHtmlPath}. Skipping archive"
                    }
                } else {
                    echo "REPORT_TYPE=${params.REPORT_TYPE} → Not archiving index.html"
                }
            }

            // Clean workspace after every build
            cleanWs()
        }
    }
}