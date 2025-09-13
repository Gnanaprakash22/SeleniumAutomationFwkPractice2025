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

                    // 🔍 DEBUG: Confirm index.html exists in root
                    sh 'ls -la index.html || echo "❌ index.html NOT found in workspace root!"'
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
            script {
                def indexHtmlPath = 'index.html'

                if (params.REPORT_TYPE == 'EXTENT_REPORT') {
                    if (fileExists(indexHtmlPath)) {
                        echo "✅ Extent Report found at ${indexHtmlPath}. Archiving and publishing..."

                        // Archive for download (optional) - include spark directory
                        archiveArtifacts artifacts: "${indexHtmlPath}, spark/**/*", allowEmptyArchive: false

                        // ✅ PUBLISH TO RENDER IN JENKINS UI — THIS IS THE FIX
                        script {
                            // Set Jenkins system property to allow JavaScript (if you have admin access)
                            try {
                                System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "")
                            } catch (Exception e) {
                                echo "Could not set CSP property (requires admin access): ${e.message}"
                            }
                        }
                        
                        publishHTML([
                            allowMissing: false,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: '.',
                            reportFiles: 'jenkins-report-wrapper.html',
                            reportName: 'Extent Report',
                            reportTitles: 'Test Execution Report',
                            escapeUnderscores: false,
                            includes: '**/*'
                        ])
                        echo "🎉 Extent Report published successfully!"

                    } else {
                        echo "⚠️ WARNING: Extent Report index.html NOT found at ${indexHtmlPath}. Skipping."
                    }
                } else {
                    echo "REPORT_TYPE=${params.REPORT_TYPE} → Not archiving or publishing index.html"
                }

                // Clean workspace — always
                cleanWs()
            }
        }
    }
}