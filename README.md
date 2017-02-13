# Introduction

A demo app showing sorting of random numeric values provided in CSV format. It uses
Spring boot,  h2 DB and Java 8.

# Running The Application

To test the sort-app, run the following commands.

* To package the application, run the following from the root directory.

        mvn package

* To run the server run.

        java -jar target/sort-app-spring-0.0.1-SNAPSHOT.jar


* It accepts list of numbers to be sorted in CSV format e.g "10,2,3,90,40"
   To access the web UI, go to http://localhost:8080

   To post data into the application.

	curl -H "Content-Type: text/plain" -X POST -d '5,8,1,2,0' http://localhost:8080/sortNumbers
	
* To see the details of the already sorted input numbers, open http://localhost:8080/sortNumbers
    or 
    curl -X GET http://localhost:8080/sortNumbers
    
# Configuring Application
You can add or change configurations in application.properties file in the src/main/resources directory.

# TODO
*  More test coverage
*  Pagination support
*  Validations
*  Logging