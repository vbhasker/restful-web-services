package com.example.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDAOService userDAOService;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return userDAOService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveUser(@PathVariable int id){
        return userDAOService.findOne(id);
    }

    @PostMapping(path = "/users")
    public ResponseEntity addUser(@RequestBody User user){
        User newUser = userDAOService.save(user);
        URI uriLocation = ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(newUser.getId())
                                    .toUri();
        return ResponseEntity.created(uriLocation).build();
    }
}
