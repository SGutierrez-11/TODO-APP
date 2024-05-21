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

        stage("Push the changed deployment file to Git") {
            steps {
                sh """
                    git config --global user.name "estebanm1812"
                    git config --global user.email "Estebanmendoza02@hotmail.com"
                    git add .
                    git commit -m "Updated Deployment of ${service} Manifest Automation with tag: ${tag}"
                """
                withCredentials([gitUsernamePassword(credentialsId: 'Jenkins-EstebanMendoza', gitToolName: 'Default')]) {
                    sh "git push https://github.com/SGutierrez-11/TODO-APP main"
                }
            }
        }

    }

}