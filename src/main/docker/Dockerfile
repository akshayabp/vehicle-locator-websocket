FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN apk update && apk add bash
RUN mkdir -p /usr/local/vehicle_locator_websocket
ADD @project.build.finalName@.jar /usr/local/vehicle_locator_websocket/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
EXPOSE 8084