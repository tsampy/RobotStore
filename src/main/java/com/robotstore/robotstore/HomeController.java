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

    @GetMapping("/findStart")
    public String findStart()
    {
        List<minimumInfo> list = new ArrayList<>();
        list = minimumRepository.findAll();

        JSONArray array = new JSONArray();
        for (minimumInfo customer : list /*minimumRepository.findAll()*/)
            array.put(customer.toString());

        JSONObject object = new JSONObject();
        object.put("start items", array);

        return object.toString().replace("\\","");
    }

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

    // GET d'un robot en fonction de son ID
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
            object.put("info", "GET was called with id " + id + " - robot not found, no id matching the request");

            return object.toString();
        }
    }

    @PostMapping
    public String createRobot()
    {
        return "HTTP POST was called";
    }

    @DeleteMapping("/robot/{id}")
    public String deleteRobot(@PathVariable String id)
    {
        if (robotsRepository.existsById(Long.parseLong(id))) {
            /*
            Optional<robotSQL> robotsOptional = robotsRepository.findById(Long.parseLong(id));
            if (!robotsOptional.isPresent())
                throw new robotNotFoundException("id-" + id);

            return robotsOptional.orElseThrow(RuntimeException::new).toString();
             */

            robotsRepository.deleteRobot(Integer.parseInt(id));
            return "DELETE robot " + id + " done";
        }
        else {
            JSONObject object = new JSONObject();
            object.put("id", "Error");
            object.put("info", "DELETE was called with id " + id + " - robot not found, no id matching the request");

            return object.toString();
        }
    }
}