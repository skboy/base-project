FROM openjdk:8-jre
RUN mkdir /app
COPY target/{你的项目}.jar /app/app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar", "--spring.profiles.active=prod"]
EXPOSE 5000
