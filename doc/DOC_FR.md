# Documentation du projet RobotStore, Thi�ry SAMPY, 2019



## D�tails du projet

Le projet RobotStore est r�alis� dans le cadre d'un test technique, demandant la mise en oeuvre d'un site full-stack de vente en ligne de robots.

La partie Front-end est bas�e sur la technologie React.
La partie Back-end est r�alis�e � l'aide de Spring Boot, Java Persistence API JPA et un serveur PostgreSQL.

Le projet est h�berg� sur un cloud Heroku, les sources sont disponibles sur GitHub.

* Apps
    * [https://robotstore.herokuapp.com/](https://robotstore.herokuapp.com/)
    * [https://robotstorereact.herokuapp.com/](https://robotstorereact.herokuapp.com/)
* Sources
    * [https://github.com/tsampy/robotstore/](https://github.com/tsampy/robotstore/)
    * [https://github.com/tsampy/robotStoreReact/](https://github.com/tsampy/robotStoreReact/)



## L'application RobotStore

L'application RobotStore a �t� �crite en Java, avec l'aide de Spring Boot.
Elle utilise JPA pour communiquer avec la base de donn�es, et proposer un serveur RESTfull, accessible au moyen de requ�tes HTTP.
Le serveur java robotstore renvoie en r�sultats des requ�tes des fichiers json.



### Commandes GET

#### /catalog

Renvoie une liste de robots disponibles � la vente :
* catalog : [liste de /catalog/id/{idrobot}]

#### /catalog/{nbItems}

Renvoie une liste ne contenant que {nbItems} robots :
* catalog : [liste de /catalog/id/{idrobot}]

#### /catalog/id/{idrobot}

Renvoie les informations commerciales d'un robot d�sign� par son {idrobot} :
* idrobot : entier
* price : entier
* promotion : pourcentage, entier
* deliveryprice : entier
* nb : entier
* available : nombre de jours avant disponibilite, entier

#### /client/{login}/{pw}

Renvoie les informations d'un utilisateur identifi� par {login} et {pw} :
* idclient : entier, serial
* login : texte
* pw : texte
* name : texte
* lastname : texte
* address : texte
* zip : texte
* city : texte
* country : texte
* mail : texte

#### /lastid

Renvoie le dernier identifiant id dans la table robots :
* lastID : entier

#### /medias/{id}

Renvoie les informations num�riques d'un robot identifi� par {id} :
* idrobot : entier
* link : texte, lien vers une image ou une vid�o
* photo : photo ou video, booleen
* caption : texte

#### /medias

Renvoie une liste de m�dias :
* medias : [liste de medias]

#### /robotspecs

Renvoie un liste de descriptions de robots :
* description : [liste de description]

Une description de robot :
* name : texte
* description : texte,
* id : entier,
* manufacturer : texte

#### /robot/{id}

Renvoie un robot identifi� par son {id}, nomm� idrobot dans la table SQL :
* idrobot : l'identifiant unique de chaque robot, de type serial.
* name : texte
* type : texte
* batterylife : en heures, entier
* manufacturer : texte
* wifi : booleen
* bluetooth : booleen
* usb : booleen
* productcode : texte
* depth : en mm, entier
* height : en mm, entier
* width : en mm, entier
* weight : en grammes, entier
* description : texte



### Commandes POST

#### /robot

Ajout d'un robot dans la table robots.
La requ�te HTTP POST doit contenir dans body un robot au format json :
* name : texte
* type : texte
* batterylife : en heures, entier
* manufacturer : texte
* wifi : booleen
* bluetooth : booleen
* usb : booleen
* productcode : texte
* depth : en mm, entier
* height : en mm, entier
* width : en mm, entier
* weight : en grammes, entier
* description : texte

#### /client

Ajout d'un client dans la table client.
La requ�te HTTP POST doit contenir dans body un client au format json :
* login : texte
* pw : texte
* name : texte
* lastname : texte
* address : texte
* zip : texte
* city : texte
* country : texte
* mail : texte

#### /robottocatalog

Ajout d'un robot dans la table catalog.
La requ�te HTTP POST doit contenir dans body un catalog au format json :
* idrobot : entier
* price : entier
* promotion : pourcentage, entier
* deliveryprice : entier
* nb : entier
* available : nombre de jours avant disponibilite, entier

#### /medias

Ajout d'un set d'informations num�riques dans la table medias.
La requ�te HTTP POST doit contenir dans body un media au format json :
* idrobot : entier
* link : texte
* photo : photo ou video, booleen
* caption : texte



### Commandes PUT

#### /robot/{id}/{nbPurchased}

Mise � jour de la table catalog de la base de donn�es.
Pour le robot d�sign� par {id}, {nbPurchased} items seront retir�s.
La requ�te HTTP PUT doit contenir dans body un user :
* login : texte



### Commandes DELETE

#### /robot/{id}

Efface de la table catalog le robot identifi� par {id}

#### /medias/{id}

Efface de la table media le media identifi� par {id}



## La base de donn�es

### Base de donnn�es utilis�e

La base de donn�es retenue pour le projet est PostgreSQL, en raison de sa licence libre.

### Les tables et leurs structures

Chaque table contient un identifiant id, de type serial.


#### Table robots

La table robots contient les informations relatives � chaque robot.
Les informations de cette table ne sont jamais effac�es, m�me lorsqu'un robot est effac�, pour �viter une perte d'informations.
En cas d'effacement par le super-utilisateur, les robots sont simplement effac�s du catalogue.

Colonnes :
* idrobot : l'identifiant unique de chaque robot, de type serial
* name : texte
* type : texte
* batterylife : en heures, entier
* manufacturer : texte
* wifi : booleen
* bluetooth : booleen
* usb : booleen
* productcode : texte
* depth : en mm, entier
* height : en mm, entier
* width : en mm, entier
* weight : en grammes, entier
* description : texte


#### Table catalog

La table catalog propose un catalogue des robots disponibles. Effacer un robot revient � l'effacer du catalogue.

Colonnes :
* idrobot : entier
* price : entier
* promotion : pourcentage, entier
* deliveryprice : entier
* nb : entier
* available : nombre de jours avant disponibilite, entier


#### Table medias

La table medias est une table contenant des liens vers des photos ou des videos des robots

Colonnes :
* idrobot : entier
* link : texte
* photo : photo ou video, booleen
* caption : texte


#### Table client

C'est la table contenant les informations des clients

* idclient : entier, serial
* login : texte
* pw : texte
* name : texte
* lastname : texte
* address : texte
* zip : texte
* city : texte
* country : texte
* mail : texte


### Communication

La communication avec le serveur java robotstore se fait au moyen de fichiers json.


#### Exemples de JSON

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