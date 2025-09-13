pipeline {
    agent any

    stages {
        stage('Test Docker') {
            steps {
                script {
                    // ✅ This uses DOCKER_HOST=tcp://dind:2375 automatically!
                    // NO docker binary needed. Uses Docker API directly.
                    withDockerContainer(image: 'busybox') {
                        sh 'echo "✅ SUCCESS! Inside Docker container via API!"'
                        sh 'ls -la'
                    }
                }
            }
        }
    }
}