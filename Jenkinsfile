pipeline {

    environment {
        registry = "cloudfun/demo-spring"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }

    agent any



    //config   system tools
    tools {
            maven 'mvn3'
    }


    stages {
        stage('CodeClone'){

            steps {
                git 'https://github.com/cloudgc/springboot-demo.git'
            }

        }


         stage('Maven') {

             //agent {
             //       //for machine node
             //       //label "for-branch-a"
             //       docker {
             //            image 'maven:3-alpine'
             //            args "-v ${JENKINS_HOME}/workspace/${JOB_NAME}:/root"
             //       }
             // }
             //steps {
             //    sh 'mvn compile test package  -f /root/pom.xml'
             //}

             steps {
                 sh 'mvn compile test package'
             }

         }

         stage('ReportTest'){

            steps{

                 script {
                    junit healthScaleFactor: 0.0 testResults '**/target/surefire-reports/*.xml'
                }

                sh "echo send test report ..."
            }
         }

         stage('DockerBuild'){

            steps {
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"

                    docker.withRegistry('', registryCredential ) {
                        dockerImage.push()
                        //remove local image
                        dockerImage.rmi()
                    }
                }
            }

        }

        stage('DockerDeploy'){

            steps {

               //sh "docker run -d $dockerImage"
               script {
                   dockerImage.run()
               }

            }

        }
    }

 }
