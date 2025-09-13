pipeline {
    agent any

    stages {
        stage('Test Java & Maven') {
            steps {
                script {
                    // ✅ This WILL WORK now — because DOCKER_HOST is set!
                    withDockerContainer(image: 'maven:3.9.6-eclipse-temurin-21') {
                        sh 'java -version'
                        sh 'mvn -v'
                        sh 'echo "✅ SUCCESS! Running Maven + Java 21 inside Docker!"'
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