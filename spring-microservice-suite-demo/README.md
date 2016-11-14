# Steps

1. Go to `spring-microservice-suite-demo` directory & compile code using: `mvn clean install`
2. Open five terminals
     * In first terminal start Eureka Registry microservice: `java -jar target/spring-microservice-registry-1.0.0.jar`
     * In second terminal start Cloud Configuration microservice: `java -jar target/spring-microservice-configserver-1.0.0.jar`
     * In third terminal start Hello World microservice: `java -jar target/spring-microservice-helloworld-1.0.0.jar`
     * In forth terminal start Foo Bar microservice: `java -jar target/spring-microservice-foobar-1.0.0.jar`
     * In fifth terminal start Zuul Gateway microservice: `java -jar target/spring-microservice-gateway-1.0.0.jar`
3. Open web browser & point to `http://localhost:8761`, you will see Eureka registry console showing all registered services.
4. Open REST client & send GET `http://localhost:8180/users` you will get response directly from helloworld-service (no gateway involved).
5. Open REST client & send GET `http://localhost:8280/users` you will get response directly from foobar-service (no gateway involved).
6. Open REST client & send GET `http://localhost:9090/api/helloworld/users` you will get response via Zuul gateway for helloworld-service (gateway is involved).
7. Open REST client & send GET `http://localhost:9090/api/foobar/users` you will get response via Zuul gateway for foobar-service (gateway is involved).
8. Open REST client & send GET `http://localhost:8280/message` you will see response which consists of a property directly read from Cloud Configuration server (using [GIT Repo](https://github.com/anishsneh/config-repo)).

> Note that while using Zuul Gateway in above example, all the requests are handled at 9090 port, clients need not be aware of 8180 & 8280 separately. There will be only a single entry point for all APIs/microservices.