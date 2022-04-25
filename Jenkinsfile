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

  stages {
    stage('コンテナのビルド') {
      steps {
        script {
          dockerImage = docker.build container
        }}}
    
    stage('コンテナの内部テスト') {
      steps {
        script {	
	   docker.image(container).inside {
             sh 'node --version'
           }}}}

    stage('コンテナの外部テスト') {
      steps {
        script {
	   echo "HELLO"
           docker.image(container).withRun('-p 8080:3000 --name testx'){
	      sh '''
	      docker ps
	      curl -i http://localhost:8080/test
	      '''
	   }}}}


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
                 anchore-cli image content $container os
                 anchore-cli image content $container npm
		 '''
		 }}}}


    stage('K8sクラスタへのデプロイ') {
      steps {    
        withKubeConfig([credentialsId: auth_k8s]) {
          sh '''	
          sed s/__BUILDNUMBER__/$BUILD_NUMBER/ webapl1.yaml > webapl1-build.yaml
          kubectl apply -f webapl1-build.yaml
          kubectl get all
	  kubectl get svc
          '''	  
        }}}}
  
  post {
      always {
          sh 'docker rmi $container'
      }}

}


