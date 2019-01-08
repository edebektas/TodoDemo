Requirements, Notes and Installment guide:

Requirements:
-An SQL server (MySql - phpmyadmin wampserver was used to create this application)
-An application server(apache-tomcat-8.5.37 was used to create this application)
-Node js
-An IDE or a way to compile the source code and create a .war file(Netbeans 8.2 was used to create this application)
 
Notes:

-Since this is an application that runs on localhost, chrome and firefox can't call post and get methods by default. 
CORS(cross-origin resource sharing) should be enabled on the browser to allow those operations.
Chrome has an extension to enable CORS, link:
https://chrome.google.com/webstore/detail/allow-control-allow-origi/nlfbmbojpeacfghkpbjhddihlkkiljbi

-Node js. Download page : https://nodejs.org/en/download/ 

Installment guide:

(Only tested on MySql - phpmyadmin wampserver application). 
-Persistence file in todoServiceSide project should be changed to match the database if another sql is used.
-tododb.sql file contains all of the tables.


-todoServiceSide should be deployed on an application server(Only tested on Tomcat). 
-index.js file client in todoclientside has a variable named api. This variable should match the todoServiceSide url.


 
-Once node js is downloaded and installed run 'npm install npm --global' on cmd to update if necessary.
-To install todoclientside run command 'npm install' while inside todoclientside folder.
-To start todoclientside run command 'npm start' while inside todoclientside folder.
-The application can be accessed from localhost:3000



