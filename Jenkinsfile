pipeline {

     environment {
         registry = "cloudfun/demo-spring"
         registryCredential = 'dockerhub'
         dockerImage = ''
     }

     agent {
             docker {
                 image 'maven:3-alpine'
                 //args '-v /root/.m2:/root/.m2'
             }
     }


     stages {
        stage('CodeClone'){

            git 'https://github.com/cloudgc/springboot-demo.git'
        }

         stage('Maven') {
             steps {
                 sh 'mvn compile test package '
             }
         }

         stage('Docker'){
            steps {
                dockerImage = docker.build registry + ":$BUILD_NUMBER"
            }

            steps {
                script {
                      docker.withRegistry('', registryCredential ) {
                        dockerImage.push()
                        dockerImage.rmi()
                      }
                 }
            }

         }
     }

 }