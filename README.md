# Project-MCSW

This is a very insecure app for academic purposes.

## How to deploy

- First let's download de repository ```git clone https://github.com/nontoa/Project-MCSW.git```.
- Then we must have a postgres database instance running in docker or locally in port 5432, see https://hub.docker.com/_/postgres.
- Then we have to create the database, see the next file [database_script](database.sql).
- There are 2 projects inside the repository, App-BackEnd and App-FrontEnd, you can run them with the command ```mvn spring-boot:run```. Keep in mind that you should use java 19 or higher.

> ⚠️ The backend will be running in port 9090 and the frontend in port 8080. Be sure you are not using these ports.
 
>> ⚠️ If you want to change the database credentials go to application.properties file in the backend project.

>  The App will be available in the next url: ```http://localhost:8080/LogBank.html``` where you can access as admin with username: user1 and password: user1.

## What you can do as Admin

- Create users, admins or auditors.
- Modify the user's balance.
- Authorize overdrafts.
- See user's transfers.

## What you can do as Auditor

- See user's transfers.

## What you can do as User

- Transfer money.
- See transfers.
- See total balance.
- Transfer money with overdraft.
