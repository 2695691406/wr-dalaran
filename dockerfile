FROM java:8

MAINTAINER WangMeng

COPY dalaran-server/target/dalaran-server-0.0.1-SNAPSHOT.jar /app.jar

CMD java -jar /app.jar

EXPOSE 8080

