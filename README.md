![logotype](https://user-images.githubusercontent.com/16310793/42381795-e2b82558-813a-11e8-99fa-09ac013d5a3e.png)
[![Build Status](https://travis-ci.org/romankh3/movietracking.svg?branch=master)](https://travis-ci.org/romankh3/movietracking)


Simple API for tracking movies with favorite actors. For movie and actor information using https://www.themoviedb.org/documentation/api 

#### API works with next requests:
* Register new user - <b>DONE</b>
* Add favorite actor - <b>DONE</b>
* Remove favorite actor - <b>DONE</b>
* Mark movie watched - <b>DONE</b>
* Search unwatched movies with favorite actors - <b>DONE</b>
* Search by month and year unviewed movies with favorite actors - <b>DONE</b>


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

run application and go to Swagger UI.
http://localhost:5000/swagger-ui.html

# Steps to reproduce API.

#### Create new User:
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
   "email": "roman.kh3@gmail.com", \ 
   "firstName": "Roman", \ 
   "lastName": "Kh3", \ 
   "password": "yoBro" \ 
 }' 'http://localhost:5000/user'

#### Show All users:
curl -X GET --header 'Accept: application/json' 'http://localhost:5000/user'

#### Add favorite actors (id: 287, 819) for User which was created above.
curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' -d '{ \ 
   "actor_id": 287, \ 
   "user_id": 0 \ 
 }' 'http://localhost:5000/actor'
 
curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' -d '{ \ 
   "actor_id": 819, \ 
   "user_id": 0 \ 
 }' 'http://localhost:5000/actor'
 
#### Get Unwatched movies for User with id=0 with favorites actors.
curl -X GET --header 'Accept: application/json' 'http://localhost:5000/movie/0'


#### Set movie with id = 550 as watched:
curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' -d '{ \ 
   "movie_id": 550, \ 
   "user_id": 0 \ 
 }' 'http://localhost:5000/movie/watched'
 
#### Get Unwatched movies for User with id=0 with favorites actors.
curl -X GET --header 'Accept: application/json' 'http://localhost:5000/movie/0'

#### Get Unwatched movies for User with id=0 with favorites actors for 1999 year.
curl -X GET --header 'Accept: application/json' 'http://localhost:5000/movie/0/1999'

#### Get Unwatched movies for User with id=0 with favorites actors for 1999 year and 12 month.
curl -X GET --header 'Accept: application/json' 'http://localhost:5000/movie/0/1999/12'
 
#### Set movie with id = 550 as unwatched:
curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' -d '{ \ 
   "movie_id": 500, \ 
   "user_id": 0 \ 
 }' 'http://localhost:5000/movie/unwatched'
 
#### Delete User:
 curl -X DELETE http://localhost:5000/user/{user_id}

### Thanks [@dee-y](https://github.com/dee-y) for designing this logo!
