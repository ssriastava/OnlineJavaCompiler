FROM openjdk:17
EXPOSE 8080
ADD target/worker.jar worker.jar
ENTRYPOINT ["java", "-jar", "worker.jar"]