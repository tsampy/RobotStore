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

    // DEBUG - server status check
    @GetMapping("/status/check")
    public String status() {
        return "working";
    }

    // catalog getter - HTTP GET request
    // returns string containing a JSON formatted list of robots
    @GetMapping("/catalog")
    public String getCatalog() {
        JSONObject object = new JSONObject();
        object.put("catalog", catalogRepository.findAll());

        return object.toString();
    }

    // catalog getter for a specified number of rows - HTTP GET request
    // returns string containing a JSON formatted list of robots
    @GetMapping("/catalog/{nbItems}")
    public String getCatalog(@PathVariable int nbItems) {
        JSONObject object = new JSONObject();
        object.put("catalog", catalogRepository.getCatalog(nbItems));

        return object.toString();
    }

    // client getter with login and password HTTP GET request
    // returns a JSON formatted string client
    @GetMapping("/client/{login}/{pw}")
    public String getClient(@PathVariable String login, @PathVariable String pw) {
        JSONObject object = new JSONObject();
        object.put("client", clientRepository.getClient(login, pw));

        return object.toString().replaceAll("\\\\", "");
    }

    // media getter for a given robot id - HTTP GET request
    // returns a JSON formatted string media
    @GetMapping("/medias/{id}")
    public String getMedia(@PathVariable String id) {
        JSONObject object = new JSONObject();
        object.put("medias", mediasRepository.getMedia(Integer.parseInt(id)));

        return object.toString();
    }

    // robot getter - HTTP GET request
    // the @PathVariable annotation maps the URI variable {id} to the method argument <id>.
    // returns a JSON formatted string robot
    @GetMapping("/robot/{id}")
    public String getRobot(@PathVariable String id) {
        if (robotsRepository.existsById(Long.parseLong(id))) {
            Optional<robotSQL> robotsOptional = robotsRepository.findById(Long.parseLong(id));
            return robotsOptional.orElseThrow(RuntimeException::new).toString();
        } else return mismatchIDerror("GET", id);
    }

    // robot creation - HTTP POST request
    //   => headers : Content-type = application/json
    //   => body : robot in json format
    // the @RequestBody annotation maps the POST request body to a robotSQL object
    @PostMapping("/robot")
    public String addRobot(@RequestBody robotSQL robot) {
        robotsRepository.addRobot(robot.getName(),
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
                robot.isUsb());
        return "Debug - robot POST request success : " + robot.toString();
    }

    @PostMapping("/client")
    public String addClient(@RequestBody clientSQL client) {
        clientRepository.addClient(client.getLogin(),
                client.getPw(),
                client.getName(),
                client.getLastname(),
                client.getAddress(),
                client.getZip(),
                client.getCity(),
                client.getCountry(),
                client.getMail());
        return "Debug - client POST request success : " + client.toString();
    }

    // adding a robot to the catalog - HTTP POST request
    //   => headers : Content-type = application/json
    //   => body : catalog in json format
    // the @RequestBody annotation maps the POST request body to a catalogSQL object
    @PostMapping("/catalog")
    public String addRobotToCatalog(@RequestBody catalogSQL catalog) {
        catalogRepository.addRobot(catalog.getIdrobot(),
                catalog.getPrice(),
                catalog.getPromotion(),
                catalog.getDeliveryprice(),
                catalog.getNb(),
                catalog.getAvailable());
        return "Debug - catalog POST request success : " + catalog.toString();
    }

    // media creation - HTTP POST request
    //   => headers : Content-type = application/json
    //   => body : media in json format
    // the @RequestBody annotation maps the POST request body to a mediasSQL object
    @PostMapping("/medias")
    public String addMedia(@RequestBody mediasSQL media) {
        mediasRepository.addLink(media.getIdrobot(),
                media.getLink(),
                media.isPhoto(),
                media.getCaption());
        return "Debug - media POST request success : " + media.toString();
    }

    // robot HTTP DELETE request
    // the @PathVariable annotation maps the URI variable {id} to the method argument <id>.
    @DeleteMapping("/robot/{id}")
    public String deleteRobot(@PathVariable String id, @RequestBody clientSQL client) {
        // login and password check : only the root superuser is allowed to delete
        if ((client.getLogin().equals("root")) &&
                (clientRepository.clientExists(client.getLogin(), client.getPw())))
            if (catalogRepository.isRobotInCatalog(Integer.parseInt(id))) {
                catalogRepository.deleteRobot(Integer.parseInt(id));
                return "DELETE robot from catalog " + id + " done";
            } else return mismatchIDerror("DELETE", id);
        else return "DELETE ERROR - Access denied";
    }

    // media HTTP DELETE request
    // the @PathVariable annotation maps the URI variable {id} to the method argument <id>.
    @DeleteMapping("/medias/{id}")
    public String deleteMedia(@PathVariable String id) {
        if (mediasRepository.existsById(Long.parseLong(id))) {
            mediasRepository.deleteRobot(Integer.parseInt(id));
            return "DELETE link from medias " + id + " done";
        } else return mismatchIDerror("DELETE", id);
    }

    public String mismatchIDerror(String error, String id) {
        JSONObject object = new JSONObject();
        object.put("id", "Error");

        switch (error.toUpperCase()) {
            case "DELETE":
                object.put("info", "DELETE was called with id " + id + " - robot not found, id mismatch");
                break;
            case "GET":
                object.put("info", "GET was called with id " + id + " - robot not found, id mismatch");
                break;
        }

        return object.toString();
    }
}