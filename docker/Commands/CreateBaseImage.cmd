@echo.
@echo Step [4/5] Creating Base Image
@echo ======================================================
@echo.

docker build --file=Environment/Dockerfiles/Dockerfile_CreateImage --tag=microservice_image:latest --rm=true .
docker rmi alpine:edge