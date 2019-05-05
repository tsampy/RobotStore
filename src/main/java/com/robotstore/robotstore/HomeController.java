/* HomeController.java - Thiéry SAMPY 2019
 *
 * the @RestController marks the class as a RESTful controller, every method returns a domain object.
 *
 * the @CrossOrigin annotation allows servers to send cross-origin requests.
 * For security purposes, only the Front-end React application should be allowed -- TO DEFINE IN RC
 */

package com.robotstore.robotstore;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {

    @Autowired
    private robotSQLRepository robotsRepository;

    @Autowired
    private catalogSQLRepository catalogRepository;

    @Autowired
    private mediasSQLRepository mediasRepository;

    @Autowired
    private clientSQLRepository clientRepository;

    @Autowired
    private robotDescriptionSQLRepository robotDescriptionRepository;

    // DEBUG - server status check
    @GetMapping("/")
    public String checkServer() {
        return createJSONString("check", "success, the robot store server is running");
    }

    // CHECKED AND APPROVED -----------------------------------------------------------------
    // HTTP GET request
    // catalog getter
    // returns string containing a JSON formatted list of robots
    @GetMapping("/catalog")
    public String getCatalog() {
        JSONObject object = new JSONObject();
        object.put("catalog", catalogRepository.findAll());

        return object.toString();
    }

    // CHECKED AND APPROVED -----------------------------------------------------------------
    // HTTP GET request
    // catalog getter for a specified number of rows
    // returns string containing a JSON formatted list of robots
    @GetMapping("/catalog/{nbItems}")
    public String getCatalog(@PathVariable int nbItems) {
        JSONObject object = new JSONObject();
        object.put("catalog", catalogRepository.getCatalog(nbItems));

        return object.toString();
    }

    // HTTP GET request
    // catalog's robot description getter for a specified idrobot
    // returns a JSON formatted catalog's robot description string
    @GetMapping("/catalog/id/{idrobot}")
    public String getDescriptionInCatalog(@PathVariable int idrobot) {
        JSONObject object = new JSONObject();

        // does the robot exist in the catalog ?
        if (catalogRepository.isRobotInCatalog(idrobot))
            return catalogRepository.getDescriptionInCatalog(idrobot).toString();
        else return mismatchIDError("GET", String.valueOf(idrobot));
    }

    // CHECKED AND APPROVED -----------------------------------------------------------------
    // HTTP GET request
    // client getter with login and password
    // returns a JSON formatted string client
    @GetMapping("/client/{login}/{pw}")
    public String getClient(@PathVariable String login, @PathVariable String pw) {
        // if the client (login and pw) is found
        if (clientRepository.clientExists(login, pw))
            return clientRepository.getClient(login, pw).toString();
        else
            // user login found
            if (clientRepository.clientExists(login))
                return requestError(login, ": mot de passe invalide");
            // user login not found
            else return requestError(login, ": utilisateur inconnu");
    }

    // HTTP GET request
    // robot getter for the last robotID in the table
    // needed to add medias and robot in catalog
    // returns a JSON formatted string media
    @GetMapping("/lastid")
    public String getLastID() {
        JSONObject object = new JSONObject();
        object.put("lastID", robotsRepository.getLastID());

        return object.toString();
    }

    // CHECKED AND APPROVED -----------------------------------------------------------------
    // HTTP GET request
    // media getter for a given robot id
    // returns a JSON formatted string media
    @GetMapping("/medias/{id}")
    public String getMedia(@PathVariable String id) {
        JSONObject object = new JSONObject();
        List<mediasSQL> medias = mediasRepository.getMedia(Integer.parseInt(id));

        if (medias.size() == 0)
            object.put("error", "media not found");
        else object.put("medias", medias);

        return object.toString();
    }

    // HTTP GET request
    // medias getter
    // returns a JSON formatted string media
    @GetMapping("/medias")
    public String getMedias() {
        JSONObject object = new JSONObject();
        object.put("medias", mediasRepository.findAll());

        return object.toString();
    }

    // HTTP GET request
    // description getter
    // returns a JSON formatted string media
    @GetMapping("/robotspecs")
    public String getRobotsDescription() {
        JSONObject object = new JSONObject();
        object.put("description", robotDescriptionRepository.findAll());

        return object.toString();
    }

    // CHECKED AND APPROVED -----------------------------------------------------------------
    // HTTP GET request
    // robot getter
    // the @PathVariable annotation maps the URI variable {id} to the method argument <id>.
    // returns a JSON formatted string robot
    @GetMapping("/robot/{id}")
    public String getRobot(@PathVariable String id) {
        // does the robot exist ?
        if (robotsRepository.existsById(Long.parseLong(id))) {
            Optional<robotSQL> robotsOptional = robotsRepository.findById(Long.parseLong(id));
            return robotsOptional.orElseThrow(RuntimeException::new).toString();
        } else return mismatchIDError("GET", id);
    }

    // CHECKED AND APPROVED -----------------------------------------------------------------
    // HTTP GET request
    // robot creation
    //   => headers : Content-type = application/json
    //   => body : robot in json format
    // the @RequestBody annotation maps the POST request body to a robotSQL object
    @PostMapping("/robot")
    public String addRobot(@RequestBody(required = false) robotSQL robot) {
        // check if a valid robot is found in the request body
        // a valid robot is not null
        // and have at least one valid variable
        if ((robot != null) && (robot.getName() != null))
            // a successful POST request returns 1
            if (robotsRepository.addRobot(robot.getName(),
                    robot.getType(),
                    robot.getManufacturer(),
                    robot.getProductcode(),
                    robot.getDescription(),
                    robot.getBatterylife(),
                    robot.getDepth(),
                    robot.getHeight(),
                    robot.getWidth(),
                    robot.getWeight(),
                    robot.isWifi(),
                    robot.isBluetooth(),
                    robot.isUsb()) == 1)
                return requestSuccess("POST", "success, " + robot.getName() + " added");
            else return requestError("POST", "an error occured while adding robot " + robot.getName());
        else return createJSONString("POST", "error, no robot detected");
    }

    // CHECKED AND APPROVED -----------------------------------------------------------------
    // HTTP POST request
    @PostMapping("/client")
    public String addClient(@RequestBody(required = false) clientSQL client) {
        // check if a valid client is found in the request body, not null and have at least one valid variable
        if ((client != null) && (client.getLogin() != null))
            // the client login can only be added if it doesn't already exist in the database
            if (!clientRepository.clientExists(client.getLogin()))
                // a successful POST request returns 1
                if (clientRepository.addClient(client.getLogin(),
                        client.getPw(),
                        client.getName(),
                        client.getLastname(),
                        client.getAddress(),
                        client.getZip(),
                        client.getCity(),
                        client.getCountry(),
                        client.getMail()) == 1)
                    return requestSuccess("POST", "success, " + client.getLogin() + " has been added");
                else return requestError("POST", "an error occured while adding client " + client.getLogin());
            else return requestError("POST", "error : the login " + client.getLogin() + " already exists");
        else return createJSONString("POST", "error, no client detected");
    }

    // CHECKED AND APPROVED -----------------------------------------------------------------
    // HTTP POST request
    // adding a robot to the catalog
    //   => headers : Content-type = application/json
    //   => body : catalog in json format
    // the @RequestBody annotation maps the POST request body to a catalogSQL object
    @PostMapping("/robottocatalog")
    public String addRobotToCatalog(@RequestBody(required = false) catalogSQL catalog) {
        // check if a valid catalog is found in the request body, not null and have at least one valid variable
        if ((catalog != null) && (catalog.getIdrobot() != 0))
            // the robot can only be added to the catalog if it doesn't already exist in the catalog
            if (!catalogRepository.isRobotInCatalog(catalog.getIdrobot()))
                // a successful POST request returns 1
                if (catalogRepository.addRobot(catalog.getIdrobot(),
                        catalog.getPrice(),
                        catalog.getPromotion(),
                        catalog.getDeliveryprice(),
                        catalog.getNb(),
                        catalog.getAvailable()) == 1)
                    return requestSuccess("POST", "success, media added : " + catalog.toString());
                else return requestError("POST", "an error occured adding media " + catalog.toString());
            else return requestError("POST", "error, robot #" + catalog.getIdrobot() + " is already in the catalog");
        else return createJSONString("POST", "error, no catalog detected");
    }

    // CHECKED AND APPROVED -----------------------------------------------------------------
    // HTTP POST request
    // media creation
    //   => headers : Content-type = application/json
    //   => body : media in json format
    // the @RequestBody annotation maps the POST request body to a mediasSQL object
    @PostMapping("/medias")
    public String addMedia(@RequestBody(required = false) mediasSQL media) {
        // check if a valid media is found in the request body, not null and have at least one valid variable
        if ((media != null) && (media.getIdrobot() != 0))
            // a successful POST request returns 1
            if (mediasRepository.addLink(media.getIdrobot(),
                    media.getLink(),
                    media.isPhoto(),
                    media.getCaption()) == 1)
                return requestSuccess("POST", "success, media added " + media.toString());
            else return requestError("POST", "an error occured adding media " + media.toString());
        else return createJSONString("POST", "error, no media detected");
    }

    // HTTP DELETE request
    // delete from the catalog a robot identified by his id
    // the @PathVariable annotation maps the URI variable {id} to the method argument <id>.
    @DeleteMapping("/robot/{id}")
    public String deleteRobot(@PathVariable String id,
                              @RequestBody(required = false) clientSQL client) {
        // check if a valid client is found in the request body, not null and have at least one valid variable
        if ((client != null) && (client.getLogin() != null))
            // login and password check : only the root superuser is allowed to delete
            if ((client.getLogin().equals("root")) &&
                    // the client exists only if login and password match
                    (clientRepository.clientExists(client.getLogin(), client.getPw())))
                if (catalogRepository.isRobotInCatalog(Integer.parseInt(id))) {
                    // a successful DELETE request returns 1
                    if (catalogRepository.deleteRobot(Integer.parseInt(id)) == 1)
                        return requestSuccess("DELETE", "robot " + id + " removed from catalog");
                    else return requestError("DELETE", "an error occured while deleting robot " + id + " from catalog");
                } else return mismatchIDError("DELETE", id);
            else return requestError("DELETE", "access denied");
        else return createJSONString("DELETE", "error, no client detected");
    }

    // HTTP DELETE request
    // delete in the media table a link given by idrobot
    // the @PathVariable annotation maps the URI variable {id} to the method argument <id>.
    @DeleteMapping("/medias/{id}")
    public String deleteMedia(@PathVariable String id,
                              @RequestBody(required = false) clientSQL client) {
        // check if a valid client is found in the request body, not null and have at least one valid variable
        if ((client != null) && (client.getLogin() != null))
            // login and password check : only the root superuser is allowed to delete
            if ((client.getLogin().equals("root")) &&
                    // the client exists only if login and password match
                    (clientRepository.clientExists(client.getLogin(), client.getPw())))
                if (mediasRepository.existsById(Long.parseLong(id))) {
                    // a successful DELETE request returns 1
                    if (mediasRepository.deleteRobot(Integer.parseInt(id)) == 1)
                        return requestSuccess("DELETE", "success : link for robot " + id + " removed from medias");
                    else return requestError("DELETE", "an error occured deleting media " + id);
                } else return mismatchIDError("DELETE", id);
            else return requestError("DELETE", "access denied");
        else return createJSONString("DELETE", "error, no client detected");
    }

    // HTTP PUT request
    // a robot has been purchased, the table catalog must be updated
    @PutMapping("/robot/{id}/{nbPurchased}")
    public String purchaseRobot(@PathVariable String id,
                                @PathVariable String nbPurchased,
                                @RequestBody(required = false) clientSQL client) {
        // check if a valid client is found in the request body, not null and have at least one valid variable
        if ((client != null) && (client.getLogin() != null))
            // only a registered user can purchase robots
            if (!clientRepository.clientExists(client.getLogin(), client.getPw()))
                return requestError("PUT", "Only registered users are allowed to purchase robots");
            else
                // a successful UPDATE request returns 1
                if (catalogRepository.purchaseRobot(Integer.parseInt(id), Integer.parseInt(nbPurchased)) == 1)
                    return requestSuccess("PUT", nbPurchased + " robots " + id + " purchased");
                else return requestError("PUT", "an error occured updating the catalog : ");
        else return createJSONString("PUT", "error, no client detected");
    }

    // ERROR MANAGEMENT ---------------------------------------------------------------------
    // JSON formatted String
    public String createJSONString(String header, String message) {
        JSONObject object = new JSONObject();
        object.put(header, message);
        return object.toString().replaceAll("\\\\", "");
    }

    // JSON formatted String for id mismatch error
    public String mismatchIDError(String httpMethod, String id) {
        return requestError(httpMethod.toUpperCase(), "was called with id " + id + " - robot not found, id mismatch");
    }

    // JSON formatted String for request error
    public String requestError(String httpMethod, String errorMessage) {
        return createJSONString("error", httpMethod + " " + errorMessage);
    }

    // JSON formatted String for request success
    public String requestSuccess(String httpMethod, String message) {
        return createJSONString("success", httpMethod + " " + message);
    }
}