pipeline {
    agent any

    stages {
        stage('Test Docker') {
            steps {
                script {
                    // This works on Docker Desktop without installing docker CLI!
                    def dockerInfo = docker.image('busybox').inside('-v /var/run/docker.sock:/var/run/docker.sock') {
                        sh 'which docker || echo "docker not found"'
                        sh 'docker --version'
                        sh 'docker ps -q'
                    }
                    echo "Docker version: ${dockerInfo}"
                }
            }
        }
    }
}