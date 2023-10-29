FROM amazoncorretto:11-alpine-jdk
VOLUME /app
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
