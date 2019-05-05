# Project RobotStore documentation, Thiéry SAMPY, 2019



## Project details

The RobotStore project is a technic test, requiring a fullstack robot website deployement.

The Front-end is realized with React.
The Back-end is created in Java with Spring Boot, Java Persistence API JPA and a PostgreSQL server.

The project is deployed on a Heroku cloud, sources are available at GitHub.

* Apps
    * [https://robotstore.herokuapp.com/](https://robotstore.herokuapp.com/)
    * [https://robotstorereact.herokuapp.com/](https://robotstorereact.herokuapp.com/)
* Sources
    * [https://github.com/tsampy/robotstore/](https://github.com/tsampy/robotstore/)
    * [https://github.com/tsampy/robotStoreReact/](https://github.com/tsampy/robotStoreReact/)



## RobotStore application

The RobotStore application is written in Java, using Spring Boot.
JPA is used tp communicate with the database, and propose a RESTfull server, available via HTTP requests.
The Java server returns json formatted results.



### Commands GET

#### /catalog

Returns a list of available robots to sell :
* catalog : [/catalog/id/{idrobot} list]

#### /catalog/{nbItems}

Returns a list of {nbItems} robots :
* catalog : [/catalog/id/{idrobot} list]

#### /catalog/id/{idrobot}

Returns commercial informations about a {idrobot} identified robot :
* idrobot : integer
* price : integer
* promotion : percent, integer
* deliveryprice : integer
* nb : integer
* available : availability delay (daytime), integer

#### /client/{login}/{pw}

Returns an user informations, identified by {login} and {pw} :
* idclient : integer, serial
* login : text
* pw : text
* name : text
* lastname : text
* address : text
* zip : text
* city : text
* country : text
* mail : text

#### /lastid

Returns the last robot ID in SQL robots table :
* lastID : integer

#### /medias/{id}

Returns digital informations of a robot for a given {id} :
* idrobot : integer
* link : text, link to a picture or a video
* photo : picture or video, boolean
* caption : text

#### /medias

Returns a list of medias :
* medias : [medias list]

#### /robotspecs

Returns a list of robots description :
* description : [description list]

A robot description :
* name : text
* description : text,
* id : integer,
* manufacturer : text

#### /robot/{id}

Returns a robot, identified by {id}, named idrobot in SQL table :
* idrobot : the unique ID for each robot, serial
* name : text
* type : text
* batterylife : in hours, integer
* manufacturer : text
* wifi : boolean
* bluetooth : boolean
* usb : boolean
* productcode : text
* depth : in mm, integer
* height : in mm, integer
* width : in mm, integer
* weight : in grams, integer
* description : text



### Commands POST

#### /robot

Add a robot in robots table.
HTTP POST request must contains a json formatted robot in body :
* name : text
* type : text
* batterylife : in hours, integer
* manufacturer : text
* wifi : boolean
* bluetooth : boolean
* usb : boolean
* productcode : text
* depth : in mm, integer
* height : in mm, integer
* width : in mm, integer
* weight : in grams, integer
* description : text

#### /client

Add a user in client table.
HTTP POST request must contains a json formatted user in body :
* login : text
* pw : text
* name : text
* lastname : text
* address : text
* zip : text
* city : text
* country : text
* mail : text

#### /robottocatalog

Add a robot in catalog table.
HTTP POST request must contains a json formatted catalog in body :
* idrobot : integer
* price : integer
* promotion : percent, integer
* deliveryprice : integer
* nb : integer
* available : availability delay (daytime), integer

#### /medias

Add a set of digital inforamtions in medias table.
HTTP POST request must contains a json formatted media in body :
* idrobot : integer
* link : text
* photo : picture or video, boolean
* caption : text



### Commands PUT

#### /robot/{id}/{nbPurchased}

Update of the catalog table in database.
For an identified by {id} robot, {nbPurchased} items are deleted.
HTTP PUT request must contains an user in body :
* login : text



### Commands DELETE

#### /robot/{id}

Delete a identified by {id} robot from catalog table.

#### /medias/{id}

Delete a identified by {id} media from medias table.



## Database

### Used database

The database used in this project is a PosgreSQL database, mostly because of its free license.

### Tables and their structures

Each table contains a serial id identifier.


#### Table robots

The robots table is the main table, and contains all the robots.
For security purpose, the elements of the robots table are never deleted, avoiding a loss of informations.
When the super-user deletes a robot, this robot is just deleted from the catalog table.

Columns :
* idrobot : the unique ID for each robot, serial
* name : text
* type : text
* batterylife : in hours, integer
* manufacturer : text
* wifi : boolean
* bluetooth : boolean
* usb : boolean
* productcode : text
* depth : in mm, integer
* height : in mm, integer
* width : in mm, integer
* weight : in grams, integer
* description : text


#### Table catalog

The table catalog proposes a set of available robots to sell.
Deleting a robot is in fact removing it from the catalog.

Columns :
* idrobot : integer
* price : integer
* promotion : percent, integer
* deliveryprice : integer
* nb : integer
* available : availability delay (daytime), integer


#### Table medias

Table containing digital informations

Columns :
* idrobot : integer
* link : text
* photo : picture or video, boolean
* caption : text


#### Table client

Table containing the clients informations

Columns :
* idclient : integer, serial
* login : text
* pw : text
* name : text
* lastname : text
* address : text
* zip : text
* city : text
* country : text
* mail : text


### Communication

The request and response with the RobotStore Java server are done using json format.


#### JSON samples

robot :
{
	"name":"robot_test01",
	"type":"robot usager",
	"batterylife":"4",
	"manufacturer":"SAMPY",
	"wifi":"true",
	"bluetooth":"false",
	"usb":"true",
	"productcode":"RU01",
	"depth":"312",
	"height":"84",
	"width":"312",
	"weight":"1225",
	"description":"Un robot aspirateur autonome"
}
catalog:
{
	"idrobot":"1",
	"price":"299",
	"promotion":"0",
	"deliveryprice":"0",
	"nb":"12",
	"available":"0"
}

client:
{
	"login":"test",
	"pw":"test",
	"name":"test01.1",
	"lastname":"test01.2",
	"address":"test01.3",
	"zip":"test01.4",
	"city":"test01.5",
	"country":"test01.6",
	"mail":"test01.7"
}

media:
{
	"idrobot":"1",
	"link":"http://www.exemple.fr/photo1.jpg",
	"photo":"true",
	"caption":"Premier modele"
}