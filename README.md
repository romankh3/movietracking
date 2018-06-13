# Movie Tracking

##### The goal of this test task is to  look at your approach to solve issues. You could implement it on any Java framework and Java 8. Share with us the result of this test task by GitHub project, also we need to have some instruction regarding how we could run and test your task. 


For movie and actor information we suggest you could use https://www.themoviedb.org/documentation/api 


Create a simple API for tracking new movies with favorite actors. API must work with next requests:
* Register new user - <b>DONE</b>
* Add favorite actor - <b>DONE</b>
* Remove favorite actor <b>DONE</b>
* Mark movie watched 
* Search by month and year unviewed movies with favorite actors


Bonus task:

* Subscribe for notifying about release movies with favorite actors by email 
* Describe and propose any feature that you think could be useful for movie fans on this simple API"

## Commands to use:

#### Create new User:
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
   "email": "roman.kh3@gmail.com", \ 
   "firstName": "Roman", \ 
   "lastName": "Kh3", \ 
   "password": "yoBro" \ 
 }' 'http://localhost:8080/user'

#### Show All users:
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/user'

#### Delete User:
curl -X DELETE http://localhost:8080/user/{user_id}

#### Swagger UI:
http://localhost:8080/swagger-ui.html