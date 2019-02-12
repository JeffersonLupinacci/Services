@echo.
@echo Step [1/1] Logging
@echo ======================================================
@echo.

call docker network create app-network

call docker-compose -f Environment\docker-compose-logging.yml up
