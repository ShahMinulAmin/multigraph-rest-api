# multigraph-rest-api
RESTful API to find all possible routes of a multigraph.

### Required environment 
  * JDK 8
  * MySQL 5.5.5 / MariaDB 10.0.34
  * Maven 3.3.3 

### How to Run
  * Clone or download the project from github repository. 
  * There is a database dump in db-backup directory. Extract the compressed file graph-2019-02-24.sql.gz.
  * Create a database with name ‘graph’. From command line, you can use the following commands:
    * Logon to mysql server:  
    `$ mysql -u [db_user] -p[db_pass]`
    * From mysql console, create the database:   
    `create database graph character set utf8 collate utf8_general_ci;`
  * Restore the provided backup database. You can use the following command from command line:  
  `$ mysql -u [uname] -p[pass] graph < graph-2019-02-24.sql`
  * In *application.properties* file of project’s resources directory, enter your database user name and password.
  * From project’s root directory, execute the following command to run the project:  
  `$ mvn clean spring-boot:run`
  * To run the unit tests, execute the following command:  
  `$ mvn clean test`
  * To run integration tests, execute the following command:  
  `$ mvn clean compile compiler:testCompile failsafe:integration-test`
  * To package the project to jar, without running the integration tests:  
  `$ mvn clean install -DskipITs`
  
### REST API Documentation
  * Swagger is used for REST API documentation. You can read the documentation using the following link:  
  http://localhost:8090/swagger-ui.html  
  List of available REST APIs can be found from the above link. Here is a sample screenshot:  
  
  ![](../assets/images/swagger-ui.png)
  
  * Detailed description of a REST API can be found clicking on the row of that API. Here is a sample screenshot:
  
  ![](../assets/images/swagger-rest-doc.png)

### Using the REST API
The REST APIs can be tested using popular REST client like Postman. Since Swagger is included in the project, the REST APIs can be tested from Swagger UI also. Another quick way to test the REST APIs is to use curl command. Some sample curl requests have been given in the following examples. Response body is also provided in most cases.
  * City controller:
    1. GET request to get list of all cities  
    `$ curl -X GET --header 'Accept: application/json' 'http://localhost:8090/api/v1/cities'` 
      
    2. GET request to get one city by id  
    `$ curl -X GET --header 'Accept: application/json' 'http://localhost:8090/api/v1/cities/1'`  
      
    * Response body:
     ```
     {
       "id": 1,
       "name": "Vasastan",
       "latitude": 59.3442327,
       "longitude": 18.0456211
     }
     ```  
     3. PUT request to update a city  
     `$ curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{  "name": "Gopalganj", "latitude": 23.0488146, "longitude": 89.8879304 }' 'http://localhost:8090/api/v1/cities/13'`  
       
     * Response body:  
      ```
      {
        "id": 13,
        "name": "Gopalganj",
        "latitude": 23.0488146,
        "longitude": 89.8879304
      }
      ``` 
      4.	POST request to create a city  
      `$ curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ "name": "khulna",  "latitude": 22.845641, "longitude": 89.5403279 }' 'http://localhost:8090/api/v1/cities'`  
  
     * Response body:  
      ```
        {
         "id": 15,
         "name": "khulna",
         "latitude": 22.845641,
         "longitude": 89.5403279
       }
      ```  
        
  * Path controller:  
    1. GET request to get list of all paths  
    `$ curl -X GET --header 'Accept: application/json' 'http://localhost:8090/api/v1/paths'`  

    2. GET request to get one path by id  
    `$ curl -X GET --header 'Accept: application/json' 'http://localhost:8090/api/v1/paths/1'`

     * Response body:  
     ```
     {
       "id": 1,
       "fromCity": "Vasastan",
       "toCity": "Liseberg",
       "container": 20,
       "transportType": "Road",
       "duration": 1,
       "cost": 480
     }
     ```  
     3. PUT request to update a path  
    `$ curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ "fromCity": "Gopalganj", "toCity": "Moulvibazar", "container": 20, "transportType": "Road", "duration": 1, "cost": 120 }' 'http://localhost:8090/api/v1/paths/35'`

     * Response body: 
     ```     
     {
       "id": 35,
       "fromCity": "Gopalganj",
       "toCity": "Moulvibazar",
       "container": 20,
       "transportType": "Road",
       "duration": 1,
       "cost": 120
     }
     ```  
     
      4.	POST request to create a path  
    `$ curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ "fromCity": "Gopalganj",  "toCity": "Moulvibazar",  "container": 20, "transportType": "Air", "duration": 1, "cost": 1220 }' 'http://localhost:8090/api/v1/paths'`

     * Response body: 
     ```  
     {
       "id": 36,
       "fromCity": "Gopalganj",
       "toCity": "Moulvibazar",
       "container": 20,
       "transportType": "Air",
       "duration": 1,
       "cost": 1220
     }
     ```  
       
  * Search controller:
    1. POST request to find all routes between a source and a destination.  
    `$ curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ "container": 20, "from": "Vasastan", "to": "Maitland", "transportTypes": [ "Road", "Ocean" ] }' 'http://localhost:8090/api/v1/routes'`   
    * Response body:
     ```
     {
       "routes": [
         {
           "route": [
             {
               "start": "Vasastan",
               "end": "Liseberg",
               "modeOfTransport": "Road",
               "cost": 480,
               "daysTaken": 1
             },
             {
               "start": "Liseberg",
               "end": "Oakland Park",
               "modeOfTransport": "Ocean",
               "cost": 1673,
               "daysTaken": 22
             },
             {
               "start": "Oakland Park",
               "end": "Maitland",
               "modeOfTransport": "Road",
               "cost": 650,
               "daysTaken": 1
             }
           ],
           "costOfRoute": 2803,
           "durationOfRoute": 24
         },
         {
           "route": [
             {
               "start": "Vasastan",
               "end": "Liseberg",
               "modeOfTransport": "Road",
               "cost": 480,
               "daysTaken": 1
             },
             {
               "start": "Liseberg",
               "end": "Georgia",
               "modeOfTransport": "Ocean",
               "cost": 1815,
               "daysTaken": 23
             },
             {
               "start": "Georgia",
               "end": "Maitland",
               "modeOfTransport": "Road",
               "cost": 650,
               "daysTaken": 1
             }
           ],
           "costOfRoute": 2945,
           "durationOfRoute": 25
         },
         {
           "route": [
             {
               "start": "Vasastan",
               "end": "Gouda",
               "modeOfTransport": "Road",
               "cost": 1480,
               "daysTaken": 3
             },
             {
               "start": "Gouda",
               "end": "Oakland Park",
               "modeOfTransport": "Ocean",
               "cost": 1673,
               "daysTaken": 18
             },
             {
               "start": "Oakland Park",
               "end": "Maitland",
               "modeOfTransport": "Road",
               "cost": 650,
               "daysTaken": 1
             }
           ],
           "costOfRoute": 3803,
           "durationOfRoute": 22
         }
       ]
     }
     ```  
     
    2. POST request to find all routes between a source and a destination. Here one location will not be directly available in the system. A nearby location will be used if it is available.  
    `$ curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ "container": 20, "from": "Himchari", "to": "Banani", "transportTypes": [ "All" ] }' 'http://localhost:8090/api/v1/routes'`  
      
    * Response body:
     ```
     {
       "routes": [
         {
           "route": [
             {
               "start": "Himchari",
               "end": "Kaptai",
               "modeOfTransport": "Road",
               "cost": 100,
               "daysTaken": 1
             },
             {
               "start": "Kaptai",
               "end": "Karail",
               "modeOfTransport": "Road",
               "cost": 115,
               "daysTaken": 1
             }
           ],
           "costOfRoute": 215,
           "durationOfRoute": 2
         },
         {
           "route": [
             {
               "start": "Himchari",
               "end": "Kaptai",
               "modeOfTransport": "Road",
               "cost": 100,
               "daysTaken": 1
             },
             {
               "start": "Kaptai",
               "end": "Agargaon",
               "modeOfTransport": "Road",
               "cost": 112,
               "daysTaken": 1
             }
           ],
           "costOfRoute": 212,
           "durationOfRoute": 2
         }
       ]
     }
     ```  
     
     
