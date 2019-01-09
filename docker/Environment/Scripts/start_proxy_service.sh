#!/bin/sh
while ! nc -z discoveryservice 8761 ; do 
echo "Waiting for the Discovery Server" 
sleep 3 
done 
cd /opt/lib/target/
java -jar service.war -Dspring.config.location=application.yml
