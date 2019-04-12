/* HomeController.java - Thiéry SAMPY 2019
 *
 * the @RestController marks the class as a RESTful controller, every method returns a domain object.
 *
 * the @CrossOrigin annotation allows servers to send cross-origin requests.
 * For security purposes, only the Front-end React application should be allowed -- TO DEFINE IN RC
 */

package com.robotstore.robotstore;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {

    @Autowired
    private minimumRepository minimumRepository;

    @Autowired
    private robotSQLRepository robotsRepository;

    // DEBUG - find minimal data
    @GetMapping("/findStart")
    public String findStart()
    {
        JSONArray array = new JSONArray();
        for (minimumInfo robot : minimumRepository.findAll())
            array.put(robot.toString());

        JSONObject object = new JSONObject();
        object.put("start items", array);

        return object.toString().replace("\\","");
    }

    // DEBUG - find data
    @GetMapping("/find")
    public String find()
    {
        List<robotSQL> list = new ArrayList<>();
        list = robotsRepository.findAll();

        JSONObject object = new JSONObject();
        object.put("robots", list);

        return object.toString();
    }

    // DEBUG - server status check
    @GetMapping("/status/check")
    public String status()
    {
        return "working";
    }

    // HTTP GET request
    // the @PathVariable annotation maps the URI variable {id} to the method argument <id>.
    // returns a JSON formatted String object
    @GetMapping("/robot/{id}")
    public String getRobot(@PathVariable String id)
    {
        if (robotsRepository.existsById(Long.parseLong(id))) {
            Optional<robotSQL> robotsOptional = robotsRepository.findById(Long.parseLong(id));
            return robotsOptional.orElseThrow(RuntimeException::new).toString();
        }
        else {
            JSONObject object = new JSONObject();
            object.put("id", "Error");
            object.put("info", "GET was called with id " + id + " - robot not found, id mismatch");

            return object.toString();
        }
    }

    // HTTP POST request
    //   => headers : Content-type = application/json
    //   => body : info in json format
    // the @RequestBody annotation maps the POST request body to a robotSQL object
    @PostMapping("/robot")
    public String createRobot(@RequestBody robotSQL robot)
    {
        robotsRepository.addRobot(robot.getInfo());
        return "Debug - POST request success, robot found : " + robot.toString();
    }

    // HTTP DELETE request
    // the @PathVariable annotation maps the URI variable {id} to the method argument <id>.
    @DeleteMapping("/robot/{id}")
    public String deleteRobot(@PathVariable String id)
    {
        if (robotsRepository.existsById(Long.parseLong(id))) {
            robotsRepository.deleteRobot(Integer.parseInt(id));
            return "DELETE robot " + id + " done";
        }
        else {
            JSONObject object = new JSONObject();
            object.put("id", "Error");
            object.put("info", "DELETE was called with id " + id + " - robot not found, id mismatch");

            return object.toString();
        }
    }
}