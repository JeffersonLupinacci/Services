set MAVEN_OPTS=-Xms1024m -Xmx3000m -Dmaven.surefire.debug=-Xmx3000m

cd C:\Users\Jefferson\Desktop\cron\cron\core-module
call mvn clean -Dlocalds install -DskipTests=true -Dmaven.test.skip=true

cd C:\Users\Jefferson\Desktop\cron\cron\data-module
call mvn clean -Dlocalds install -DskipTests=true -Dmaven.test.skip=true

cd C:\Users\Jefferson\Desktop\cron\cron\application-service
call mvn clean -Dlocalds install -DskipTests=true -Dmaven.test.skip=true
