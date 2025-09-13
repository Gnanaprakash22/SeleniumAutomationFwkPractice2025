pipeline {
    agent any

    stages {
        stage('Test Java & Maven') {
            steps {
                script {
                    // Run inside a clean Maven + Java 21 container
                    withDockerContainer(image: 'maven:3.9.6-eclipse-temurin-21') {
                        sh 'java -version'
                        sh 'mvn -v'
                        sh 'echo "✅ SUCCESS! Java and Maven are running inside Docker!"'
                    }
                }
            }
        }
    }

    post {
        always {
            echo "Build finished!"
            cleanWs()
        }
    }
}