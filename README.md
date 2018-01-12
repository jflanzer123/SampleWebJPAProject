# SampleWebJPAProject
Spring Boot MVC JPA Spring Data Project

This is a project for the purpose of providing an example of using Spring Boot and Spring MVC to build a web app with persistence
using JPA, Spring Data, and an embedded H2 database. The app persists two entities with a one to many relationship. Each Cusotmer 
can have several Invoice Lines. The user is able to select a customer and add invoice lines to that customer. The app also uses an 
embedded Tomcat server. The views use HTML, HTML5, Bootstrap,and JSTL. Input validation is done on the browser side as well as the 
server side. The server side input validation checks the database to determine if the input customer's id matches an existing customer.
Because of the brevity of this app, much of the logic appears in the controller rather than being partitioned into services and 
DAO/repositories. A single interface implementing the Jpa Repository is instantiated for each entity. This enables the entity management 
to be accomplished behind the scene by Spring Data. 
