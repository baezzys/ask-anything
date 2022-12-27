cd spring-gateway && ./gradlew clean build && cd .. && 
cd spring-post && ./gradlew clean build && cd ..
mv ./spring-gateway/build/libs/gateway-0.0.1-SNAPSHOT.jar ./spring-gateway/target &&
mv ./spring-post/build/libs/post-0.0.1-SNAPSHOT.jar ./spring-post/target &&
docker-compose build
