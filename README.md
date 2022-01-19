![This](https://clipart-best.com/img/popcorn/popcorn-clip-art-51.png)

# :film_projector: About the project 
This application displays the operation of the cinema with all its functions. The application uses Spring, Hibernate and is built on Rest principles. Configured authorization and authentication. The user can have the user role or the admin role.

# :mag: Overview 

List of all endpoints: 

- POST: /register - all
- GET: /cinema-halls - user/admin
- POST: /cinema-halls - admin
- GET: /movies - user/admin
- POST: /movies - admin
- GET: /movie-sessions/available - user/admin
- GET: /movie-sessions/{id} - user/admin
- POST: /movie-sessions - admin
- PUT: /movie-sessions/{id} - admin
- DELETE: /movie-sessions/{id} - admin
- GET: /orders - user
- POST: /orders/complete - user
- PUT: /shopping-carts/movie-sessions - user
- GET: /shopping-carts/by-user - user
- GET: /users/by-email - admin

# :gear: Technologies 
- Java 11
- Hibernate
- Spring Framework
- REST
- MySQL
- Lombok (v1.18.20)
- Apache Tomcat 9.0.54 (to run app locally)

# :link: Installation
Download and install MySQL.
Download and install Tomcat (version 9.0.54).
Download MySQL Workbench and create schema (if you need).
Add your information in db.properties file in src/main/resources folder.  If you use another DBMS change hibernate.dialect, as well.
Run this app with Tomcat local server.
You will be redirected to login page. You can use username bob@gmail.com with password 1234 to login as admin.
You can also create your own user by sending a POST request to this url : `/register`
If you want to send another requests instead GET use POSTMAN or other similar app.
