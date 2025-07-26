package net.digest.journalAPP.controller;


import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all=userService.getAll();
        if(!all.isEmpty()) return new ResponseEntity<>(all, HttpStatus.OK);
        return new ResponseEntity<>("We can't fetch anything NO+CONTENT! ", HttpStatus.OK);
    }

    @PostMapping("/add-admin")
    public ResponseEntity<?> addNewAdmin(@RequestBody User user){
        User newAdmin = userService.saveNewAdmin(user);
        return new ResponseEntity<>(newAdmin,HttpStatus.CREATED);
    }
}
