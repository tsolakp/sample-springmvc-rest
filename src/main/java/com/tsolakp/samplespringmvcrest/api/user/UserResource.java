package com.tsolakp.samplespringmvcrest.api.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping(value="/user")
public class UserResource{

    @Autowired
    private UserService userService = null;
    
    @RequestMapping( method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> getUsers() {
        return userService.getUsers();
    }    
    
    @RequestMapping( method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, 
                    produces=MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody User registerUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }
    
    @RequestMapping( value="/{username}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> deleteUser(@PathVariable("username") String userName) {
        Long deletedCount = userService.deleteUser(userName);
        return deletedCount == 0 ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
