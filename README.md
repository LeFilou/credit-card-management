# Credit card management system
> An application that manages credit card using DDD, CQRS and Axon framework

This application is an implementation of [this example](https://spring.io/blog/2018/04/11/event-storming-and-spring-with-a-splash-of-ddd) 
using [Axon framework](https://axoniq.io/).

## Event storming
![Event Storming](https://i.imgur.com/vBhouxJ.png)
For more information, please visit https://spring.io/blog/2018/04/11/event-storming-and-spring-with-a-splash-of-ddd.

## run
> Please make sure you have an axon server running before starting the app,
> or run it with docker-compose
### run in dev mode

`java -Dspring.profiles.active=debug CreditCardManagementApplication`

### docker build

``` bash
# build jar, image, then run it
mvn clean package && docker build -t secret-keeper . && docker run -d -p 8080:8080 -e PROFILE=development,debug --name credit-card-management && docker ps | grep "credit-card-management"
```

### docker compose

``` bash
# run docker-compose
docker-compose up
```