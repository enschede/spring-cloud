FROM java:openjdk-8-jdk
ADD /target/eureka-naming-service-0.0.1-SNAPSHOT.jar //eureka-naming-service.jar
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka-naming-service.jar"]
