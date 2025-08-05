package net.digest.journalAPP.service;

import lombok.extern.slf4j.Slf4j;
import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.*;

//controller ---> service ---> repository

@Component
@Slf4j
public class UserService {

@Autowired
private UserRepository userRepository;

private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void saveEntry(User user){
        userRepository.save(user);
    }
    public User saveNewUser(User user){
        //System.out.println("Saving user: " + user.getUsername());
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Arrays.asList("USER"));
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            //logger.error("Error occurred while saving user", e);
            log.error("Error occurred while saving user", e);
            throw new RuntimeException("Failed to save user");
        }
    }

    public User saveNewAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
        return user;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }
    public void deleteJournalEntryId(ObjectId id){
        userRepository.deleteById(id);
    }
    public void deleteByUserName(String userName){
        userRepository.deleteByUsername(userName);
    }
    public User findByUserName(String userName){
        return userRepository.findByUsername(userName);
    }
}