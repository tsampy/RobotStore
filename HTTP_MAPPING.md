# RobotStore
Java server HTTP mapping

* GET REQUESTS ----------------------------------------
    * /check : check the server status
    * /catalog : returns the catalog of available robots
    * /catalog/{nbItems} : returns a defined number of catalog's items
    * /client/{login}/{pw} : returns informations about a user, defined by login and pw
    * /medias/{id} : returns medias for a identified robot id
    * /robot/{id} : returns a robot, defined by its id

* POST REQUESTS (only allowed for superuser root)------
    * /robot : robotSQL : add a robot in the database 
    * /client : clientSQL : add a user
    * /robottocatalog : catalogSQL : add a existing robot in catalog
    * /medias : mediasSQL : add media for a given robot id

* DELETE REQUESTS (only allowed for superuser root) ---
    * /robot/{id} : clientSQL :
    * /medias/{id} : clientSQL :

* PUT REQUESTS (only allowed for superuser root)-------
    * /robot/{id}/{nbPurchased} : clientSQL : change the number of available robots in catalog for a given robot id
