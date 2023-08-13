# Demo
HCA Parallon Java Code Exercise

mvn clean install

mvn spring-boot:run

Access the H2 Console: With the above configuration in place, you can access the H2 Console by opening your web browser and navigating to the URL: http://localhost:8080/h2-console.

Enter Connection Details: On the H2 Console login page, you'll see a form to enter the database connection details:

JDBC URL: This should match the spring.datasource.url value from your configuration. In the example above, it's jdbc:h2:mem:testdb.
Username: Use the spring.datasource.username value from your configuration. In the example above, it's sa.
Password: Leave this field empty or use the spring.datasource.password value from your configuration.
Login to H2 Console: After entering the connection details, click the "Connect" button to log in to the H2 Console.

Use H2 Console: Once you're logged in, you'll have access to the H2 Console's web interface. You can execute SQL queries, view tables, and interact with the in-memory database.

http://localhost:8080/swagger-ui.html

 // System.out.println(account); Simple Fix Log


 When you create a new account via postman, make sure you use Raw json to do so 
    example {
    "firstName": "Jane",
    "lastName": "Doe"
}
 Make sure you go to Headers and create a new Content-Type Key and in the value add "application/json" The create will not work otherwise. 

 All other CRUD can be done with a simple form-data, but make sure after a new customer is created that you change the Content-Type back to "multipart/form-data; boundary=<calculated when request is sent>"