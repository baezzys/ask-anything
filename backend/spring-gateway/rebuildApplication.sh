cd spring-gateway && ./gradlew clean build && cd .. && 
mv ./spring-gateway/build/libs/gateway-0.0.1-SNAPSHOT.jar ./target && 
docker-compose build
