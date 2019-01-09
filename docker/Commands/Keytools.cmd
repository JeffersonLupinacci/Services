@echo.
@echo Step [4/7] Keytools
@echo ======================================================
@echo.

cd Environment
cd Deployment

del *.cer
del *.jks

SET PASSWORD=123456

@echo Create Discovery
keytool -genkey -alias "Discovery" -keyalg RSA -keysize 2048 -validity 45 -keystore "Discovery.jks" -storetype JKS -dname "CN=discoveryservice,OU=Discovery Service,O=Jefferson Lupinacci,L=A Coruna,ST=Galicia, C=ES" -keypass %PASSWORD% -storepass %PASSWORD% -noprompt

@echo Create Proxy
keytool -genkey -alias "Proxy" -keyalg RSA -keysize 2048 -validity 45 -keystore "Proxy.jks" -storetype JKS -dname "CN=proxyservice,OU=Proxy Service,O=Jefferson Lupinacci,L=A Coruna,ST=Galicia,C=ES" -keypass %PASSWORD% -storepass %PASSWORD% -noprompt

@echo Create Auth
keytool -genkey -alias "Auth" -keyalg RSA -keysize 2048 -validity 45 -keystore "Auth.jks" -storetype JKS -dname "CN=authservice,OU=Auth Service,O=Jefferson Lupinacci,L=A Coruna,ST=Galicia,C=ES" -keypass %PASSWORD% -storepass %PASSWORD% -noprompt

@echo Create Application
keytool -genkey -alias "Application" -keyalg RSA -keysize 2048 -validity 45 -keystore "Application.jks" -storetype JKS -dname "CN=applicationservice,OU=Application Service,O=Jefferson Lupinacci,L=A Coruna,ST=Galicia,C=ES" -keypass %PASSWORD% -storepass %PASSWORD% -noprompt

@echo Create Scheduler
keytool -genkey -alias "Scheduler" -keyalg RSA -keysize 2048 -validity 45 -keystore "Scheduler.jks" -storetype JKS -dname "CN=schedulerservice,OU=Scheduler Service,O=Jefferson Lupinacci,L=A Coruna,ST=Galicia,C=ES" -keypass %PASSWORD% -storepass %PASSWORD% -noprompt

@echo.

@echo Export Discovery 
keytool -exportcert -alias "Discovery" -keystore "Discovery.jks" -file "DiscoveryPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD% 

@echo Export Proxy
keytool -exportcert -alias "Proxy" -keystore "Proxy.jks" -file "ProxyPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%

@echo Export Auth
keytool -exportcert -alias "Auth" -keystore "Auth.jks" -file "AuthPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%

@echo Export Application
keytool -exportcert -alias "Application" -keystore "Application.jks" -file "ApplicationPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%

@echo Export Scheduler
keytool -exportcert -alias "Scheduler" -keystore "Scheduler.jks" -file "SchedulerPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%

@echo.

@echo Import Discovery
keytool -importcert -alias "Proxy" -keystore "Discovery.jks" -file "ProxyPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%
keytool -importcert -alias "Auth" -keystore "Discovery.jks" -file "AuthPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%
keytool -importcert -alias "Application" -keystore "Discovery.jks" -file "ApplicationPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%
keytool -importcert -alias "Scheduler" -keystore "Discovery.jks" -file "SchedulerPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%

@echo Import Proxy
keytool -importcert -alias "Discovery" -keystore "Proxy.jks" -file "DiscoveryPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%
keytool -importcert -alias "Auth" -keystore "Proxy.jks" -file "AuthPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%
keytool -importcert -alias "Application" -keystore "Proxy.jks" -file "ApplicationPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%
keytool -importcert -alias "Scheduler" -keystore "Proxy.jks" -file "SchedulerPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%

@echo Import Auth
keytool -importcert -alias "Discovery" -keystore "Auth.jks" -file "DiscoveryPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%
keytool -importcert -alias "Proxy" -keystore "Auth.jks" -file "ProxyPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%
keytool -importcert -alias "Application" -keystore "Auth.jks" -file "ApplicationPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%
keytool -importcert -alias "Scheduler" -keystore "Auth.jks" -file "SchedulerPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%

@echo Import Application
keytool -importcert -alias "Discovery" -keystore "Application.jks" -file "DiscoveryPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%
keytool -importcert -alias "Auth" -keystore "Application.jks" -file "AuthPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%
keytool -importcert -alias "Proxy" -keystore "Application.jks" -file "ProxyPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%

@echo Import Scheduler
keytool -importcert -alias "Discovery" -keystore "Scheduler.jks" -file "DiscoveryPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%
keytool -importcert -alias "Auth" -keystore "Scheduler.jks" -file "AuthPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%
keytool -importcert -alias "Proxy" -keystore "Scheduler.jks" -file "ProxyPublic.cer" -noprompt -storepass %PASSWORD% -keypass %PASSWORD%

del *.cer

cd ..
cd ..