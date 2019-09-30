##Rock Paper Scissor for Lottoland

This project is based on Spring boot application 

#### Technology used
- Java 8 : programming language 
- Spring boot : Web application 
- H2 : In-Memory Database
- Maven : Building project
- Swagger : RESTful Documentation

#### Web Application configuration 
- Web Application is running on port `9091`
- You can change the configuration in `application.properties`
 
#### Steps to run the project

> Prerequisite
- Maven 
- Java 8 or higher 

> Start the application

Through following command:

    mvn clean install spring-boot:run 
    
Maven will install all the dependencies and followed by run the application

> To test the application you can see the documentation in:

    http://localhost:9091/swagger-ui.html
    
    And I attach a postman collection with all REST operation allowed:
    
    src\main\resources\Paper-Rock-Scissors-Lottoland.postman_collection.json
    
    
#### Architectural points and Terminologies used in the project

> Game rules
 - Rock beats Scissors
 - Scissors beats Paper
 - Paper beats Rock
 * More info in https://en.wikipedia.org/wiki/Rock-paper-scissors

    
> RESTful API design 

Backend consists RESTful APIs all starts with `/api/v1` because of following reasons:
- Api versioning can be done in future.

> Trying out and documentation for RESTful APIs

Swagger is already integrated and can be used for using API instead of curl. 

    http://localhost:9091/swagger-ui.html

> Comments in code 

- Entire code styling is influenced by Clean Code principle
- Front-End have been develoop in other project using React, HTML, CSS, JS, Visual Studio Code and deploy in port:3000
- To run this app you must to install Node.js and npm (https://www.npmjs.com/get-npm)
- Go to the folder and: npn start. Then the app will launch in one explorer 
