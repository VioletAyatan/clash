FROM openjdk:17

ENV JAR_NAME clash.jar

WORKDIR /opt/project/

COPY target/server-1.0-SNAPSHOT.jar /opt/project/$JAR_NAME

EXPOSE 8080

CMD java -jar $JAR_NAME