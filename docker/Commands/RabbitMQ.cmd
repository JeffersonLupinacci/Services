@echo.
@echo Step [1/1] Rabbit MQ
@echo ======================================================
@echo.

call docker network create app-network

call docker-compose -f Environment\docker-compose-rabbitmq.yml up --build
