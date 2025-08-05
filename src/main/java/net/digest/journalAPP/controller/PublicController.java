package net.digest.journalAPP.controller;


import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        User saveUser = userService.saveNewUser(user);
        return new ResponseEntity<>(saveUser,HttpStatus.CREATED);
    }
}
