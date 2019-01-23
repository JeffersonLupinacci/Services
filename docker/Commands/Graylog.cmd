@echo.
@echo Step [1/1] Graylog
@echo ======================================================
@echo.

call docker-compose -f Environment\docker-compose-graylog.yml up
