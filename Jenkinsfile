pipeline {
    agent {
        kubernetes {
            yaml '''
apiVersion: v1
kind: Pod
metadata:
  labels:
    label: agent
spec:
  containers:
    - name: helm
      image: lachlanevenson/k8s-helm:latest
      command:
      - cat
      tty: true
'''
        }
    }

    environment {
        service = "your-service-name" // Define the service variable
        tag = "your-tag" // Define the tag variable
    }

    stages {

        stage("Cleanup Workspace") {
            steps {
                cleanWs()
            }
        }
    
        stage("Checkout from SCM") {
            steps {
                git branch: 'main', credentialsId: 'Jenkins-EstebanMendoza', url: 'https://github.com/SGutierrez-11/TODO-APP'
            }
        }
    
        stage("Update the Deployment Tags") {
            steps {
                script {
                    def service = 'your-service-name' // Update with your service name
                    def tag = 'your-tag' // Update with your tag
                    sh """
                        cat ./charts/${service}/values.yaml
                        sed -i 's/^tag: .*/tag: ${tag}/' ./charts/${service}/values.yaml 
                        cat ./charts/${service}/values.yaml
                    """
                }
            }
        }

        stage('Install Helm') {
            steps {
                script {
                    // Verify if Helm is installed
                    def helmInstalled = sh(script: "which helm || true", returnStdout: true).trim()
                    if (helmInstalled == '') {
                        // Download and install Helm
                        echo 'Installing Helm...'
                        sh '''
                            curl -fsSL https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 | bash
                        '''
                    } else {
                        echo 'Helm is already installed'
                    }
                    // Verify Helm version to ensure it is installed correctly
                    sh 'helm version'
                }
            }
        }

        stage("Update deployment in cluster") {
            steps {
                container('helm') {
                    sh "helm install ${service} ./charts/${service}"
                }
            }
        }
    }
}
