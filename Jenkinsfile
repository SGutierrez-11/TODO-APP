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
                    sh """
                        ls
                        cat ./charts/${service}/values.yaml
                        sed -i 's/^tag: .*/tag: ${tag}/' ./charts/${service}/values.yaml 
                        cat ./charts/${service}/values.yaml
                    """
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
