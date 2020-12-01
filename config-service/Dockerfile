FROM openjdk:11.0.9.1-jdk
COPY target/*.jar app.jar
ENV JAVA_OPTS="
ENTRYPOINT ["java", "-jar", "/app.jar"]