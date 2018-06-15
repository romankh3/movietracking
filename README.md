# Movie Tracking

Simple API for tracking new movies with favorite actors. For movie and actor information using https://www.themoviedb.org/documentation/api 

#### API works with next requests:
* Register new user - <b>DONE</b>
* Add favorite actor - <b>DONE</b>
* Remove favorite actor - <b>DONE</b>
* Mark movie watched - <b>DONE</b>
* Search unwatched movies with favorite actors - <b>DONE</b>
* Search by month and year unviewed movies with favorite actors - <b>IN PROCESS</b>


#### Nice to Have:

* Subscribe for notifying about release movies with favorite actors by email 
* Describe and propose any feature that you think could be useful for movie fans on this simple API"

# How to run:
```$xslt
$ git clone https://github.com/romankh3/movietracking
$ cd movietracking
$ ./run.sh
```

# Rest API:

#### Delete User:
curl -X DELETE http://localhost:8080/user/{user_id}

#### Create new User:
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
   "email": "roman.kh3@gmail.com", \ 
   "firstName": "Roman", \ 
   "lastName": "Kh3", \ 
   "password": "yoBro" \ 
 }' 'http://localhost:8080/user'

#### Show All users:
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/user'

#### Show the most popular movies
curl -X GET --header 'Accept: text/plain' 'http://localhost:8080/movie'

#### Set movie with id = 550 as watched:
curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' -d '{ \ 
   "movie_id": 550, \ 
   "user_id": 0 \ 
 }' 'http://localhost:8080/movie/watched'
 
#### Set movie with id = 550 as unwatched:
curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' -d '{ \ 
   "movie_id": 500, \ 
   "user_id": 0 \ 
 }' 'http://localhost:8080/movie/unwatched'

#### Swagger UI:
http://localhost:8080/swagger-ui.html