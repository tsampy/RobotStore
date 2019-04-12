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

    // DEBUG
    @GetMapping("/findStart")
    public String findStart()
    {
        //List<minimumInfo> list = new ArrayList<>();
        //list = minimumRepository.findAll();

        JSONArray array = new JSONArray();
        for (minimumInfo robot : minimumRepository.findAll())
            array.put(robot.toString());

        JSONObject object = new JSONObject();
        object.put("start items", array);

        return object.toString().replace("\\","");
    }

    // DEBUG
    @GetMapping("/find")
    public String find()
    {
        List<robotSQL> list = new ArrayList<>();
        list = robotsRepository.findAll();

        JSONObject object = new JSONObject();
        object.put("robots", list);

        return object.toString();
    }

    @GetMapping("/status/check")
    public String status()
    {
        return "working";
    }

    // requete GET d'un robot en fonction de son ID
    // fonction renvoyant un String au format JSON
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

    // requete POST formatée avec
    //   => headers : Content-type = application/json
    //   => body : json contenant les informations
    // les informations sont récupérées par le paramètres robot, au format robotSQL
    @PostMapping
    public String createRobot(@RequestBody robotSQL robot)
    {
        robotsRepository.addRobot("Debug - robot insertion 01");
        return "Debug - POST request success, robot found : " + robot.toString();
    }

    // requete DELETE d'un robot en fonction de son ID
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