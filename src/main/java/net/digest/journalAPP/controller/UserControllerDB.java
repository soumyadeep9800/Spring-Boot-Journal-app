package net.digest.journalAPP.controller;


import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//controller ---> service ---> repository

@RestController
@RequestMapping("/user")
public class UserControllerDB {

    @Autowired
     private UserService userService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            System.out.println("userName"+ userName);
            User userInDb = userService.findByUserName(userName);
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            //return ResponseEntity.ok("User updated successfully");
    }
    @DeleteMapping
     public ResponseEntity<?> DeleteUser(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            //System.out.println("userName "+ userName);
            userService.deleteByUserName(userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
