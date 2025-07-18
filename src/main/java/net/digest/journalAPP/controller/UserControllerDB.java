package net.digest.journalAPP.controller;


import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//controller ---> service ---> repository

@RestController
@RequestMapping("/user")
public class UserControllerDB {

    @Autowired
     private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        try {
            User userInDb = userService.findByUserName(userName);
            if (userInDb != null) {
                userInDb.setUsername(user.getUsername());
                userInDb.setPassword(user.getPassword());
                userService.saveEntry(userInDb);
                //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                return ResponseEntity.ok("User updated successfully");
            }
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user: " + e.getMessage());
        }
    }
}
