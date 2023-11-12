## Stage 1 : build with maven builder image with native capabilities
FROM container-registry.oracle.com/graalvm/native-image:17 AS build

ARG workspace

COPY $workspace/target/*-fat.jar /app/app.jar
COPY $workspace/target/classes/conf /app/conf
COPY $workspace/target/classes/logback.xml /app/logback.xml
WORKDIR /app
RUN native-image -jar app.jar

## Stage 2 : create the final image, need a glibc or musl alpine
FROM frolvlad/alpine-glibc
COPY --from=build /app/origin /app/application
COPY --from=build /app/conf /app/conf
COPY --from=build /app/logback.xml /app/logback.xml
EXPOSE 8082
WORKDIR /app/
ENTRYPOINT [ "/app/application" ]
