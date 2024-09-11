FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY fileToRead.csv fileToRead.csv
ENTRYPOINT ["java","-jar","/app.jar"]