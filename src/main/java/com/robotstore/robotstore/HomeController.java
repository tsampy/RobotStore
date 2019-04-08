package com.robotstore.robotstore;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
public class HomeController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private minimumRepository minimumRepository;

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
    //public List<Users> find()
    public String find()
    {
        List<Users> list = new ArrayList<>();
        list = usersRepository.findAll();

        JSONObject object = new JSONObject();
        object.put("items", list);

        //return usersRepository.findAll();
        return object.toString();
    }

    @GetMapping("/status/check")
    public String status()
    {
        return "working";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/robot/{id}")
    public String getUser(@PathVariable String id)
    {
        if (usersRepository.existsById(Long.parseLong(id))) {
            Optional<Users> usersOptional = usersRepository.findById(Long.parseLong(id));
            Users users = usersOptional.orElseThrow(RuntimeException::new);

            /*return "HTTP Get was called, find robot by id : " + id
                    + " - robot found : " + users.toString();*/
            return  users.toString();
        }
        else return "HTTP Get was called, find robot by id : " + id
                   + " - robot not found !";
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/http-servlet-response")
    public String usingHttpServletResponse(HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, POST, DELETE");
        response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, X-Auth-Token, X-Csrf-Token, WWW-Authenticate, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "false");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Baeldung-Example-Header", "Value-HttpServletResponse");
        return "Response with header using HttpServletResponse";
    }

    @PostMapping
    public String createUser()
    {
        return "HTTP POST was called";
    }

    @DeleteMapping("/robot/{userId}")
    public String deleteUser(@PathVariable String userId)
    {


        return "HTTP DELETE was called";
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable String userId)
    {


        return "HTTP PUT was called";
    }
}