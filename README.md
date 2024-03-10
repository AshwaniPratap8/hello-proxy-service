# hello-proxy-service

This is the Quarkus based application that performs a grpc call to a SOAP endpoint.

## Run the application in dev mode

```shell script
mvn quarkus:dev
```

> **_NOTE:_** 

Access the Quarkus dev UI at -> http://localhost:8080/q/dev-ui/.


## Package and run the application

The application can be packaged using.

```shell script
mvn clean package
```
Once packaged properly, we can run the application with below command.

`java -jar target/quarkus-app/quarkus-run.jar`
