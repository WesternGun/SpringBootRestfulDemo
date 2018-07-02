package com.privalia.mydemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.privalia.mydemo.domain.Bin_info;


@RestController
public class MyDemoController {
    //imperative to synchronize when we iterate the map!! On the map.
    static Map<Long, Bin_info> users = Collections.synchronizedMap(new HashMap<>());
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping(value="/bins", method=RequestMethod.GET)
    public List<Bin_info> getAllBins() {
        List<Bin_info> allUsers = new ArrayList<>(users.values());
        return allUsers;
    }
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postUser() {
        
    }

}
