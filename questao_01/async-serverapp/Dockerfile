FROM openjdk:8-jdk-alpine

WORKDIR /home

COPY target/async-serverapp-jar-with-dependencies.jar async-serverapp-jar-with-dependencies.jar
COPY target/async-receiver-jar-with-dependencies.jar async-receiver-jar-with-dependencies.jar

COPY run.sh run.sh

EXPOSE 10992

CMD ["sh", "run.sh"]

