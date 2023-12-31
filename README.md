# OnlineOA
### An online application and approval system for courses.

## About
This project is a Web application for school students and teachers to select courses and approve applications. 
It aims to help teachers and students simplify the process of course selection. 
This system also develops an administrator background for the staff of the academic affairs office to facilitate the management of students, teachers, courses, applications and other information.
![image](https://github.com/NUMS-half/OnlineOA/assets/99391471/31e7fbd3-c368-4c2e-bacd-b43423c79819)

## Develop Environment
- IDE：IntelliJ IDEA 2023.1.2
- DBMS：MySQL 8.0.32
- OS：Windows 11
- Web Server：Apache Tomcat 9.0.45
- Dependency Manage：Apache Maven 3.9.1

## File Dictionary Tree
```bash
├─src
│  ├─main
│  │  ├─java
│  │  │  └─cn
│  │  │      └─edu
│  │  │          └─neu
│  │  │              └─onlineoa
│  │  │                  ├─bean
│  │  │                  ├─controller
│  │  │                  ├─filter
│  │  │                  ├─mapper
│  │  │                  ├─service
│  │  │                  └─utils
│  │  ├─resources
│  │  │  └─cn
│  │  │      └─edu
│  │  │          └─neu
│  │  │              └─onlineoa
│  │  │                  └─mapper
│  │  └─webapp
│  │      ├─imgs
│  │      └─WEB-INF
│  └─test
│      ├─java
│      │  └─test
│      └─resources
```

## Preview
### General
Login

![image](https://github.com/NUMS-half/OnlineOA/assets/99391471/f6e12456-0b45-4a03-a2f6-45cf463472e6)


### Student Side
Course Apply

![image](https://github.com/NUMS-half/OnlineOA/assets/99391471/fb3a0493-3b78-44c1-92dc-25d112a1082f)


### Teacher Side
Application Approve

![image](https://github.com/NUMS-half/OnlineOA/assets/99391471/11bca0f2-5c25-4f1d-8f7e-af155722e0e7)


### Admin Side
Admin Homepage

![image](https://github.com/NUMS-half/OnlineOA/assets/99391471/5b65500a-48db-4beb-ab8b-282db2fae0e7)

User Manage

![image](https://github.com/NUMS-half/OnlineOA/assets/99391471/de0f27a6-aa12-44bb-be1c-03402f6095f9)


## Getting Started
### Environment preparation
- JDK: 1.8.0
- MySQL: 8.0.32
- Tomcat Server: 9.0.45

### Install
Download the war file in the release and run it on your machine.
Deploy the WAR package to Tomcat：
1. Navigate to Tomcat's webapps directory, usually under the Tomcat installation directory.
2. Copy the WAR package to the webapps directory.
3. Tomcat will automatically extract the WAR file and deploy the application.
Start Tomcat:

Start the Tomcat server：
```
../tomcat/bin/startup.sh
```

Access the application:
```
http://localhost:8080/OnlineOA_war_exploded
```

## ©️ Copyright and License
This project is licensed under the MIT-License.
