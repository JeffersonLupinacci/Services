@echo Step [2/7] Compiling with Maven

cd..
call mvn clean install -DskipTest=true -q

@echo Step [3/7] Copying Deployment Packages
@echo ======================================================
@echo.
copy discovery-service\target\discovery-service.war docker\Environment\Deployment\
copy proxy-service\target\proxy-service.war docker\Environment\Deployment\
copy auth-service\target\auth-service.war docker\Environment\Deployment\
copy application-service\target\application-service.war docker\Environment\Deployment\
copy scheduler-service\target\scheduler-service.war docker\Environment\Deployment\

cd docker