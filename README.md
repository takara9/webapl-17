# webapl-17


## はじめかた

~~~
$ cd k8s-yaml
$ kubectl secret -f secret.yaml
$ kubectl create configmap mysql-config --from-file=mysql.conf.d
$ cd ../src/main/resources
$ kubectl create configmap application.prop --from-file=application.properties
~~~