@echo.
@echo Step [5/5] Docker Compose Build 
@echo ======================================================
@echo.

call docker volume create --name=config-repo
call docker network create app-network

call docker-compose -f Environment\docker-compose-app.yml up --build
