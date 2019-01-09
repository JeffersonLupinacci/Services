@echo.
@echo Step [1/1] Postgres SQL
@echo ======================================================
@echo.

call docker network create app-network

call docker-compose -f Environment\docker-compose-db.yml up --build
