def globalVar = "0"
pipeline {

    environment {
        registry = "cloudfun/demo-spring"
        registryCredential = 'dockerhub'
        dockerImage = ''
        hasContainer = '0'
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

         stage('ParallelStage'){

           parallel {
                stage('ReportTest'){

                    steps{
                         script {
                            junit allowEmptyResults: true, healthScaleFactor: 0.0, testResults: '**/target/surefire-reports/*.xml'
                        }
                        sh "echo send test report ..."
                    }
                }

                stage('SonaQube'){

                    steps {

                        echo "send code to sonaqube ====>>>>"

                    }

                }

           }


         }

         stage('DockerBuild'){

            steps {
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"

                    docker.withRegistry('', registryCredential ) {
                        dockerImage.push()
                        //remove local image
                       // sh "docker rmi ${registry}:$BUILD_NUMBER"
                    }

                    globalVar = sh(       script: "docker ps -a|grep spring-demo |wc -l",
                                            returnStdout: true
                                   ).trim()

                }
            }

        }

        stage('DockerDeploy-new'){

            when{

                expression {
                    return globalVar == '0';
                }
            }

            steps {
                script{

                    sh "docker run -d --name spring-demo -p  8098:8080 ${registry}:$BUILD_NUMBER  "
                }
            }

        }

        stage('DockerDeploy-re'){

            when{

                expression {
                    return globalVar == '1';
                }
            }

            steps {
                script{
                    sh "docker stop spring-demo"
                    sh "docker rm spring-demo"
                    sh "docker run -d --name spring-demo -p  8098:8080 ${registry}:$BUILD_NUMBER  "
                }
            }

        }
    }

 }
