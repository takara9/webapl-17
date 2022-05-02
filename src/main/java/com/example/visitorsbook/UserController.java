package com.example.visitorsbook;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
*/

@RestController
public class UserController {


	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;
    @CrossOrigin(origins = "http://localhost:8080")


   	@PostMapping("/user")
	User addUser(@RequestBody User user) {
	    System.out.println("==== post add user ==== ");
	    userRepository.save(user);
	    return user;
	}

    @GetMapping(path="/users")
    public @ResponseBody Iterable<User> getAllUsers() {
	    System.out.println("==== get all user ==== ");
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }    

    @GetMapping("/user/{id}")
    User one(@PathVariable Long id) {
        User user = userRepository.findById(id)
        return assembler.toModel(user);
    }

}
