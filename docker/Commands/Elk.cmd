@echo.
@echo Step [1/1] Elasticsearch, Logstash, Kibana (ELK) 
@echo ======================================================
@echo.

call docker-compose -f Environment\docker-compose-elk.yml up
