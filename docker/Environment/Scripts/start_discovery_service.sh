#!/bin/sh
cd /opt/lib/target/
java -jar service.war --spring.config.location=application.yml
