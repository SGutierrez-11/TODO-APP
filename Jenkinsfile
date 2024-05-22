pipeline {
    agent any

    stages {
        stage('Install Helm') {
          steps {
            container('helm:3.8.2-alpine') { // Replace with desired Helm version
              script {
                sh 'helm version' // Verify Helm installation
              }
            }
          }
        }

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
    

    
        stage("Update the Deployment Tags (using Helm Template)") {
            steps {
           
               sh """
                    cat ./charts/${service}/values.yaml
                    sed -i 's/^tag: .*/tag: ${tag}/' ./charts/${service}/values.yaml 
                    cat ./charts/${service}/values.yaml
                """

              }
            }
        }

        stage("Update deployment in cluster") {
            steps {
              sh """
                helm install ${service} \\
                  --set image.tag=${tag} \\  # Provide the tag value here
                  ./charts/${service}
              """
            }
        }
    }

