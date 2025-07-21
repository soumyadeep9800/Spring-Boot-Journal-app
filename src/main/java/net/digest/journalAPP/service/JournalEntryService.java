package net.digest.journalAPP.service;


import net.digest.journalAPP.entity.JournalEntry;
import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//controller ---> service ---> repository
@Component
public class JournalEntryService{

@Autowired
private JournalEntryRepository journalEntryRepository;
@Autowired
private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user =userService.findByUserName(userName);
            JournalEntry save=journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(save);
            userService.saveEntry(user);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void deleteJournalEntryId(ObjectId id,String userName){
        User user=userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x ->x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}