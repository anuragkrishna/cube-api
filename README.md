# cube-api

API Routes

1.	For creating a user
POST 		/user
request body: {"name": "mayank", "city" : "meerat"}
response: {"id": 1, "name": "mayank", "city" : "meerat"}

2.	For creating a cube
POST 		/user/:user_id:/cube
	request body: {"name": "btech project"}
	response: {"id": 1, "name": "btech project", "user_id": 1}

3.	For creating a content
POST 		/user/:user_id:/content
	request body: {"link": "http://opencv.org/"}
	response: {"id": 1, "link": "http://opencv.org/", "user_id": 1}

4.	For adding a content to a cube
POST 		/user/:user_id:/cube/:cube_id:/content
request body: {"content_id": 1}
	response: {"id": 1 (optional), "cube_id": 1, "content_id": 1}

5.	For deleting a content from a cube
DELETE 	/user/:user_id:/cube/:cube_id:/content/:content_id:

6.	For deleting a cube
DELETE 	/user/:user_id:/cube/:cube_id:

7.	For sharing a cube with other user
POST 		/user/:user_id:/cube/:cube_id:/share
request body: {"user_id": 2}
	response: {"id": 1 (optional), "cube_id": 1, "user_id": 2}

8.	For sharing a content with other user
POST 		/user/:user_id:/content/:content_id:/share
request body: {"user_id": 2}
response: {"id": 1 (optional), "content_id": 1, "user_id": 2}	

9.	For listing all cube of a user
GET 		/user/:user_id:/cube
response: [{"id": 1, "name": "btech project", "user_id": 1},
{"id": 2, "name": "btech project 2", "user_id": 1}]

10.	For listing all contents of a user
GET 		/user/:user_id:/content
response: [{"id": 1, "link": "http://opencv.org/", "user_id": 1},
{"id": 2, "link": "http://google.com/", "user_id": 1},
{"id": 3, "link": "http://cubeit.io/", "user_id": 1}]


How to Run:

1. Download the war file to tomcat\webapps\
2. DB Config:
   Start a mysql server on localhost:3306
   Create a db cubeitDB1
3. Test according to the routes and the test cases.   
   

