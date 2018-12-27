pipeline {

    environment {
        registry = "cloudfun/demo-spring"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }

    //cron conditiion
    //triggers {
    //        cron('H */4 * * 1-5')
    //}

    agent any


    //agent{
    //     node {
    //            label 'my-defined-label'
    //            customWorkspace '/some/other/path'
    //        }
    //}


    //config by system tools
    //tools {
    //        maven 'apache-maven-3.0.1'
    //}


    stages {
        stage('CodeClone'){

            steps {
                git 'https://github.com/cloudgc/springboot-demo.git'
            }

            // condicondition
            //when {
            //    branch 'production'
            //    //anyOf
            //    allOf {
            //        branch 'production'
            //        environment name: 'DEPLOY_TO', value: 'production'
            //    }
            //}


        }

        stage('Example') {
            steps {

                echo 'Hello World'

                script {
                    def browsers = ['chrome', 'firefox']
                    for (int i = 0; i < browsers.size(); ++i) {
                        echo "Testing the ${browsers[i]} browser"
                    }
                }
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

            junit healthScaleFactor:  0.0 testResults '**/target/surefire-reports/*.xml'

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
    }

 }