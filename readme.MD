# Customer Complaint Management API

API is developed by using following technologies:
1. Spring Boot.
2. Spring Web MVC.
3. REST.
4. Spring Data Mongo.
5. Swagger2 for API documentation.
6. Mongodb.
7. Gradle


API is Tested by using following test dependencies:
1. JUnit.
2. Mockito.
3. MockMVC.

This setup  is dependent on following prerequisite:
1. Mongodb installation on your machine.
2. JRE or JDK installed on your machine.

Configuration Steps:
Edit <resources>/application.properties file and application-embedded.properties to include your local mongo db connection.
  
Important Commands:

To verify test covered hit following command.

./gradlew test

To run application:

./gradlew bootRun < application will be started on http://localhost:8087 >

To see generated test reports navigate to:

<project_installation_directory>/build/reports/tests/test/index.html


  







