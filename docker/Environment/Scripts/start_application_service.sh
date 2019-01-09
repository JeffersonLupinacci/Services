#!/bin/sh
while ! nc -z discoveryservice 8761 ; do 
echo "Waiting for the Discovery Server" 
sleep 3 
done 
while ! nc -z db 5432 ; do 
echo "Waiting for the PostgresSQL Server" 
sleep 3 
done 
cd /opt/lib/target/
java -jar service.war -Dspring.config.location=application.yml
