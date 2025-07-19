package net.digest.journalAPP.service;


import net.digest.journalAPP.entity.JournalEntry;
import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class JournalEntryService{

@Autowired
private JournalEntryRepository journalEntryRepository;
@Autowired
private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName){
        User user =userService.findByUserName(userName);
        JournalEntry save=journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(save);
        userService.saveEntry(user);
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

    public void deleteJournalEntryId(ObjectId id,String userName){
        User user=userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x ->x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}


//controller ---> service ---> repository