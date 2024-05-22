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
    

    
        stage("Update the Deployment Tags (using Helm Template)") {
            steps {
           
               sh """
                    cat ./charts/${service}/values.yaml
                    sed -i 's/^tag: .*/tag: ${tag}/' ./charts/${service}/values.yaml 
                    cat ./charts/${service}/values.yaml
                """

              }
            }

        stage('Install Helm') {
            steps {
                script {
                    // Verificar si Helm está instalado
                    def helmInstalled = sh(script: "which helm || true", returnStdout: true).trim()
                    if (helmInstalled == '') {
                        // Descargar e instalar Helm
                        echo 'Installing Helm...'
                        sh '''
                            curl -fsSL https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 | bash
                        '''
                    } else {
                        echo 'Helm is already installed'
                    }
                    // Verificar la versión de Helm para asegurarse de que está instalado correctamente
                    sh 'helm version'
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
}
