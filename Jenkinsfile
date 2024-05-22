pipeline {
    agent any

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
                sh """
                    cat ./charts/${service}/values.yaml
                    sed -i 's/^tag: .*/tag: ${tag}/' ./charts/${service}/values.yaml 
                    cat ./charts/${service}/values.yaml
                """
            }
        }

        stage("Update deployment in cluster") {
            steps {
               
               helm install ${service} ./charts/${service} -namespace todo-app
               
                }
            }
        }

    }
