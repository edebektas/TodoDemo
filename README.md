Requirements, installment guide and notes:
 Used MySql - phpmyadmin but any db probably would be fine (Only tested on MySql - phpmyadmin wampserver application). 
But persistence file in todoServiceSide project should be changed to match the database.
 tododb.sql file contains all the tables in sql format.


 todoServiceSide should be deployed on an application server. I used apache-tomcat-8.5.37 but any server probably would be fine (Only tested on Tomcat). 
index.js file client in todoclientside has a variable named api. This variable should match the todoServiceSide url.


 Node js. Download page : https://nodejs.org/en/download/ 
 Once node js is downloaded and installed run 'npm install npm --global' on cmd to update if necessary.
 To install todoclientside run command 'npm install' while inside todoclientside folder.
 To start todoclientside run command 'npm start' while inside todoclientside folder.
 the application can be accessed from localhost:3000


 Netbeans was used to develop both applications.

 Since this is an application that runs on localhost, chrome and firefox cant call post and get methods by default. 
CORS(cross-origin resource sharing) should be enabled on the browser to allow those operations.
Chrome has an extension to enable CORS, link:
https://chrome.google.com/webstore/detail/allow-control-allow-origi/nlfbmbojpeacfghkpbjhddihlkkiljbi
