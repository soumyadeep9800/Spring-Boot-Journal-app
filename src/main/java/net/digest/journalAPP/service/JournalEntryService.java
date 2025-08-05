package net.digest.journalAPP.service;

import net.digest.journalAPP.entity.JournalEntry;
import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//controller ---> service ---> repository
@Component
//@Service
public class JournalEntryService{

@Autowired
private JournalEntryRepository journalEntryRepository;
@Autowired
private UserService userService;

    //@Transactional
    public void saveEntryTwoParameter(JournalEntry journalEntry, String userName){
        try{
            User user =userService.findByUserName(userName);
            //System.out.println("User: " + user);
            JournalEntry save=journalEntryRepository.save(journalEntry);
            //System.out.println(save);
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

    //@Transactional
    public void deleteJournalEntryId(ObjectId id,String userName){
        User user=userService.findByUserName(userName);
        boolean remove = user.getJournalEntries().removeIf(x ->x.getId().equals(id));
        if(remove){
            userService.saveEntry(user);
            journalEntryRepository.deleteById(id);
        }
        else {
            System.out.println("problem happen in JournalEntryService:deleteJournalEntryId");
        }
    }
}