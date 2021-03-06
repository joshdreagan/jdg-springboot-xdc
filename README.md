# JDG Spring Boot Cross DC

## Requirements

- [Apache Maven 3.x](http://maven.apache.org)

## Preparing

Build the project source code

```
$ cd $PROJECT_ROOT
$ mvn clean install
```

## Running the example standalone

```
$ cd $PROJECT_ROOT
$ mvn spring-boot:run
```

## Running the example in OpenShift

It is assumed that:

- OpenShift platform is already running, if not you can find details how to [Install OpenShift at your site](https://docs.openshift.com/container-platform/3.9/install_config/index.html).

Create a new project:

```
$ oc new-project demo-jdg
```

Create the [ServiceAccount](https://kubernetes.io/docs/tasks/configure-pod-container/configure-service-account/):

```
$ oc create -f src/main/kube/serviceaccount.yml
```

Create the [ConfigMap](https://kubernetes.io/docs/user-guide/configmap/):

```
$ oc create -f src/main/kube/configmap.yml
```

Create the [Secret](https://kubernetes.io/docs/concepts/configuration/secret/):

```
$ oc create -f src/main/kube/secret.yml
```

Add the [Secret](https://kubernetes.io/docs/concepts/configuration/secret/) to the [ServiceAccount](https://kubernetes.io/docs/tasks/configure-pod-container/configure-service-account/) created earlier:

```
$ oc secrets add sa/jdg-springboot-xdc-sa secret/jdg-springboot-xdc-secret
```

Add the view role to the [ServiceAccount](https://kubernetes.io/docs/tasks/configure-pod-container/configure-service-account/):

```
$ oc login -u system:admin
$ oc policy add-role-to-user view system:serviceaccount:demo-jdg:default -n demo-jdg
$ oc policy add-role-to-user view system:serviceaccount:demo-jdg:jdg-springboot-xdc-sa -n demo-jdg
$ oc login -u developer
$ oc policy add-role-to-user view system:serviceaccount:jdg-springboot-xdc:jdg-springboot-xdc-sa
```

The example can be built and run on OpenShift using the following goals:

```
$ mvn clean install fabric8:resource fabric8:build fabric8:deploy
```

## Testing the code

Use the same tools/instructions for testing standalone (above). Make sure to change the host/port to point to the exposed OpenShift Route.
