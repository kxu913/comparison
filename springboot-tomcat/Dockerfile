FROM openjdk:17

ARG workspace

COPY $workspace/target/*.jar /app/app.jar
WORKDIR /app
EXPOSE 8083
ENTRYPOINT ["java","-jar", "./app.jar" ]
