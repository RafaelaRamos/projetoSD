cd async-share
mvn install
cd ..

cd async-clientapp
mvn clean compile assembly:single
cd ..

cd async-sender-pull
mvn clean compile assembly:single
mv target/async-sender-jar-with-dependencies.jar ../async-clientapp/target/
cd ..

cd async-serverapp
mvn clean compile assembly:single
cd ..

cd async-receiver-pull
mvn clean compile assembly:single
mv target/async-receiver-jar-with-dependencies.jar ../async-serverapp/target/
cd ..

clear

sudo docker-compose -f docker-compose.yml up

