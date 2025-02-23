pipeline {
    agent any
    
    tools {
        maven 'mvn'  // This should match the tool name you configured in Jenkins if needed
    }
    
    environment {
        SCANNER_HOME = tool 'SonarQubeScanner'  // Ensure SonarQubeScanner is configured in Jenkins
        IMAGE_NAME = 'my-app-image'            // Name of the Docker image
        CONTAINER_NAME = 'my-app-container'    // Name of the Docker container
    }

    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', changelog: false, poll: false, url: 'https://github.com/rajk68501/java.git'
            }
        }
        
        stage('Code Compile') {
            steps {
                sh 'mvn compile'  // Compiles the code
            }
        }
        
        stage('Run Test Cases') {
            steps {
                sh 'mvn test'  // Runs tests
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('sonar') {  // Ensure 'sonar' matches the configured name in Jenkins
                        def sonarResult = sh(script: """
                            ${tool 'SonarQubeScanner'}/bin/sonar-scanner \
                            -Dsonar.projectKey=my-project-key \
                            -Dsonar.projectName=my-project-name \
                            -Dsonar.sources=.
                        """, returnStatus: true)
                        if (sonarResult != 0) {
                            currentBuild.result = 'FAILURE'
                            error "SonarQube analysis failed."
                        }
                    }
                }
            }
        }
        
        stage('Maven Build') {
            steps {
                sh 'mvn clean package'  // Ensure the build produces a .jar file
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image using the Maven-generated .jar file
                    sh 'docker build -t ${IMAGE_NAME} .'  // Using environment variable IMAGE_NAME
                }
            }
        }
        
        stage('Stop & Remove Existing Container') {
            steps {
                script {
                    // Stop and remove the container if it exists
                    sh """
                        docker ps -q -f name=${CONTAINER_NAME} | xargs -r docker stop
                        docker ps -a -q -f name=${CONTAINER_NAME} | xargs -r docker rm
                    """
                }
            }
        }

        stage('Run Docker Container') {
            when {
                expression {
                    return currentBuild.result == null || currentBuild.result == 'SUCCESS'
                }
            }
            steps {
                script {
                    // Run the Docker container only if the build was successful
                    sh "docker run -d -p 8082:8082 --name ${CONTAINER_NAME} ${IMAGE_NAME}"
                }
            }
        }

        stage('Smoke Test') {
            steps {
                script {
                    // Run a simple curl command to check if the application is running
                    def response = sh(script: 'curl -s -o /dev/null -w "%{http_code}" http://localhost:8082', returnStdout: true).trim()
                    if (response != '200') {
                        error "Smoke Test failed. Server returned HTTP code: ${response}"
                    } else {
                        echo "Smoke Test passed. Server is up and running!"
                    }
                }
            }
        }

        stage('Load Test') {
            steps {
                script {
                    // You can add a load testing tool like Apache JMeter here. This is a simple placeholder for load testing.
                    echo "Performing Load Test"
                    // For example, using Apache Benchmark:
                    sh 'ab -n 100 -c 10 http://192.168.239.143:8082/'
                }
            }
        }
    }

    post {
        always {
            script {
                // Clean up Docker resources after the build, regardless of the result
                sh 'docker system prune -f'
            }
        }

        success {
            echo 'Pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed!'
        }
    }
}
