FROM gradle:8.3.0-jdk17 AS build
RUN mkdir -p /root/java/application
COPY --chown=gradle:gradle ../.. /root/java/application/src
WORKDIR /root/java/application/src
RUN gradle build --no-daemon

FROM openjdk:17-oracle
COPY --from=build /root/java/application/src/gateway/build/libs/gateway-0.0.1-SNAPSHOT.jar /root/java/application/spring-boot-application.jar


CMD ["java","-jar", "/root/java/application/spring-boot-application.jar"]