package net.digest.journalAPP.controller;

import net.digest.journalAPP.entity.JournalEntry;
import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.service.JournalEntryService;
import net.digest.journalAPP.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

//controller ---> service ---> repository

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerDB {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
          User user =userService.findByUserName(userName);
          List<JournalEntry> all= user.getJournalEntries();
          if(!all.isEmpty()) return new ResponseEntity<>(all,HttpStatus.OK);
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userName}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body("Invalid journal entry data");
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {
        return journalEntryService.findById(myId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId,@PathVariable String userName){
        journalEntryService.deleteJournalEntryId(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry, @PathVariable String userName){
        JournalEntry old=journalEntryService.findById(myId).orElse(null);
        if(old !=null){
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            old.setTitle(newEntry.getTitle() !=null && !newEntry.getTitle().equals("") ? newEntry.getTitle(): old.getTitle());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
