# Hemtest
A text analyzing software in form of an web-api call interface written in Java with the Spring Boot framework.
- Console Application (Run in your favorite terminal)
# Building Instructions:
This is a maven program that can be imported into your favorite IDE like Eclipse or IntelliJ IDEA.
The main class is located in com.hemtest.main.HemtestStartupApp
The pom.xml contains some spring boot dependencies, so make sure to update the project after import.

- To change port number, either go to src/main/resources and edit the application.yml or you can use the --server.port=<portnr> as launch argument

# Call the API through a POST call.
url: localhost:8080/count if using the .jar file, else the default port is set to 3000 in the application.yml config file.
Example call:
$> curl -H "Content-type: text/plain" -X "POST" -d "Banan Äpple Katt Hund Banan Hund Katt Hund" http://localhost:3000/count
$> {"Hund":3,"Banan":2,"Katt":2,"Äpple":1}

The webapi does ignore numbers and special chars like ;<>{}
