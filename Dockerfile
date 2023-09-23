FROM openjdk:17-oracle
RUN mkdir -p /root/java/application
WORKDIR /root/java/application
COPY ./build/libs/Lab1-1.0.0.jar /root/java/application/application.jar

CMD ["java", "-jar", "application.jar"]