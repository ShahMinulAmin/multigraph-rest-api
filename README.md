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
  
