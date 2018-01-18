# SampleWebJPAProject
Spring Boot MVC JPA Spring Data Project

This is a project for the purpose of providing an example of using Spring Boot and Spring MVC to build a web app with persistence
using JPA, Spring Data, and an embedded H2 database. The app persists two entities with a one to many relationship. Each Customer 
can have several Invoice Lines. The user is able to select a customer and add invoice lines to that customer. The app also uses an 
embedded Tomcat server. The views use HTML, HTML5, Bootstrap,and JSTL. Input validation is done on the browser side as well as the 
server side. The server side input validation checks the database to determine if the input customer's id matches an existing customer.
A single interface implementing the Jpa Repository is instantiated for each entity. This enables the entity management 
to be accomplished behind the scene by Spring Data. There is an autowired service for business logic, and one for validation.
The business service calculates an invoice subtotal, tax and total for each customer which are formatted by a Number Format static method
in the controller, just prior to sending them to the view.


