#!groovy
def jenkins_label = "cloud&&centos" // jenkins build-agent label expression
def jdk_version = "8u121"
def image = "openjdk:${jdk_version}"

node(jenkins_label) {
    docker.image("${image}").pull() //Get image
    stage "Checkout source"
    checkout scm
    sh "git clean -fxd"
    def branch = env.BRANCH_NAME

    docker.image("${image}").inside('-v /mnt/data/jenkins/.m2:/.m2 -v /mnt/data/jenkins/.gradle:/.gradle') {
        try {
            stage "Build and Test"
            echo("Building Branch ${branch}")
            sh "chmod +x ./gradlew"
            sh "./gradlew build"

            stage "Generate Code Coverage Report"
            sh "./gradlew jacocoTestReport"
            
			if (branch == "master") {
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'art-bobcat_service_account_id', passwordVariable: 'password', usernameVariable: 'username']]) {
                	stage "Publish To Artifactory"
                	sh "./gradlew publish" 
                }
			}
        } finally {
            //Publish Test reports
            stage 'Publish Test and Code coverage Report'
            publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: 'Tests Report'])
            publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'build/reports/codecoverage/test/html', reportFiles: 'index.html', reportName: 'Code Coverage Report'])

        }
    }

}
