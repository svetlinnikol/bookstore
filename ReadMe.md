# Getting Started

### How to run
To run the application follow the following steps:
1. Go to project main directory(It's where this ReadMe file is)
2. Execute mvn clean install(make sure you have Docker running with exposed port 2375)
3. Execute docker-compose up -d
### How to use
Swagger url - http://localhost:1234/user-service/swagger-ui.html
### Implementation notes
For this example I have implemented some features for the user-service. Other services are shown in the architecture in the bookstore.drawio file. 
The architecture file can be opened at https://app.diagrams.net/. The whole spplication is setuped via docker. The docker image is build via maven.
For the different microservices we'll use different db schemas. I haven't used much spring security for simplicity.