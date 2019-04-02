#!/bin/sh
echo "********************************************************"
echo "Starting the Eureka Server"
while ! `nc -z eurekaserver 8761`; do sleep 3; done
echo "******* Eureka Server has started"

echo "********************************************************"
echo "Waiting for the Zipkin server to start on port 9411"
echo "********************************************************"
while ! `nc -z zipkinserver 9411`; do sleep 3; done
echo "******** Zipkin Server has started "


java -Dspring.profiles.active=docker -jar /usr/local/vehicle_locator_websocket/@project.build.finalName@.jar
