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

Entire REST UI documentation and  REST clinet UI to consume REST endpoint is included in Swagger2 UI.
<Swagger 2 URL is as follow:>

http://localhost:8087/swagger-ui.html

# Swagger as REST Endpoint client

## List All Endpoints

Swagger UI at http://localhost:8087/swagger-ui.html shows all REST endpoints. Click on show that will display following methods!

![List All Endpoints](/images/endpoints_list.png)

## Submit Customer Complaint via POST Request

Click on POST and then below that you'll find POST method endpoint:

Fill the JSON body and click try it now! button to submit request and analyse returns

![POST usage](/images/post_usage.png)

## Submit GET by id and GET request to verify submitted Customer Complaint via POST request.

Submitted post request you can validate through GET and GET all endpoints sample forms to do that are available below:

You can get these forms by clicking GET buttons from swagger UI. Hit Try it out! button to Submit request.

![GET usage](/images/get_usage.png)

![GET_ALL usage](/images/getall_usage.png)

## Update Customer Complaint via PUT Request

You can update Customer Complaint resource via Put form which is listed below. Add update json in below form. 

You can get this form by clicking Put button from swagger UI. Hit Try it out! button to Submit request.

![PUT usage](/images/put_usage.png)

You can verify updated resource in GET and GETALL forms!


## Delete Customer Complaint via DELETE Request

You can delete Customer Complaint resource via Delete form which is listed below. Add Delete json in below form. 

You can go to this form by clicking DELETE button from swagger UI. Hit Try it out! button to Submit request.

![DELETE usage](/images/delete_usage.png)

You can verify updated resource in GET and GETALL forms!

Thanks!





  







