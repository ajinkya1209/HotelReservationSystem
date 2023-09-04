# project-group-Repo
for CDAC project and we are group 9 and our project is Hotel Reservation System 
*************************************************************************************************************************************************************************

Pre-requisites: Mysql v8.0+, java jdk-version 17+ 
Technologies And Softwares Used: STS v3.9.18, Intellij Idea community Edition 2022.3.2, Visual Studio Code v1.18.1, MySql Workbench v8.0.33, React.JS v18.2.0 , Java 17 , npm v9.8.1 , Spring Security v3.1

Visual Studio Code:
Version: 1.81.1 (user setup)
Node.js: 16.17.1
V8: 10.8.168.25-electron.0
OS: Windows_NT x64 10.0.22621

API Testing : Postman v10.17.4
UI Testing: Mozilla Firefox v116.0+ / Google Chrome v116.0+

******************************************************************-----Steps to run the project-----*******************************************************************************

Pre-requisites: Mysql v8.0+, java jdk-version 17+ 

1. 	Open STS -> File -> Import -> Maven -> Existing Maven projects -> Next
	Browse for spring-boot-project exctracted from zip 
	And click on "Select Folder" Make sure "pom.xml" is checked and click Finish
	(Make sure there is network connectivity)

2.	Expand the file structure goto src/main/resources -> open application.properties file

3.	#Replace hotel by database name of your choice and 3306 by port number of your mysqld port
	spring.datasource.url=jdbc:mysql://localhost:3306/hotel?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true

	#replace root by your mysql username
	spring.datasource.username=root

	#replace root by your mysql password 
	spring.datasource.password=root

	Configure this file according to your credentials

4.	Right click on spring-boot-project and goto Maven-> update project
	Make sure spring-boot-project is checked and Force update of snapshots/Releases is checked
	click OK

5.	Right click on spring-boot-project->Run as -> spring boot App

6.	This should start the backend.

7.	Open Visual Studio Code and Add the Hotel-Reservation-App to workspace

8. 	Click on Terminal->New Terminal (make sure the current working directory is hotel-reservation-app)

9.	In terminal fire the command
	npm start
	(this will start the react-app which will open in the browser)
