pipeline {
  agent any
  environment {
    reg_server   = "harbor.labo.local"
    repository   = "k8s/webapl-17"
    container    = "${ reg_server }/${ repository }" + ":$BUILD_NUMBER"
    registry_url = "https://${ reg_server }"
    auth_regi    = "harbor-login"
    auth_k8s     = "k8s4-test"
  }

  tools {
      maven 'mvn-3.8.5'
  }

  stages {

    stage ('ソースコードのテスト') {
      steps {
        sh 'mvn -Dmaven.test.failure.ignore=true test' 
      }
      post {
        success {
           junit 'target/surefire-reports/**/*.xml' 
        }}}


    stage('コンテナのビルド') {
      steps {
        script {
          dockerImage = docker.build container
        }}}


    stage('コンテナレジストリへプッシュ') {
      steps {
        script {
          docker.withRegistry(registry_url, auth_regi) {
            dockerImage.push()
          }}}}


    stage('コンテナの脆弱性検査とSBOM作成') {
      steps {
          script {
      	      withCredentials([string(credentialsId: "anchore-login", variable: 'ANCHORE_CLI_PASS')]) {
	         sh '''
                 export ANCHORE_CLI_URL=http://localhost:8228/v1
                 export ANCHORE_CLI_USER=admin
		 anchore-cli image add  $container
                 anchore-cli image wait $container
                 anchore-cli image vuln $container all
		 #anchore-cli evaluate check $container --detail
                 #anchore-cli image content $container os
                 #anchore-cli image content $container npm
		 '''
		 }}}}


    stage('K8sクラスタへのデプロイ') {
      steps {
        script {
	   withCredentials([string(credentialsId: "argocd-deploy-role", variable: 'ARGOCD_AUTH_TOKEN')]) {
              sh '''
              export ARGOCD_SERVER="argocd-server.argocd.k8s4.labo.local"
              APP_NAME="webapl-17"
              IMAGE_DIGEST=$(docker image inspect $container -f '{{join .RepoDigests ","}}')
              argocd --grpc-web app set  $APP_NAME --insecure --kustomize-image $IMAGE_DIGEST
              argocd --grpc-web app sync $APP_NAME --insecure --force --timeout 600
              argocd --grpc-web app wait $APP_NAME --insecure --timeout 600
              '''
	   }}}}}
  
  post {
      always {
          sh 'docker rmi $container'
	  //sh 'docker system prune --all --force'
      }}

}


