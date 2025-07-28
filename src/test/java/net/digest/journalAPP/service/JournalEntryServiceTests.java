package net.digest.journalAPP.service;

import net.digest.journalAPP.entity.JournalEntry;
import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.repository.JournalEntryRepository;
import net.digest.journalAPP.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JournalEntryServiceTests {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        journalEntryRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testSaveEntry() {
        JournalEntry entry = new JournalEntry();
        entry.setTitle("Test Entry");
        entry.setContent("This is a test entry.");
        entry.setDate(LocalDateTime.now());

        journalEntryService.saveEntry(entry);

        List<JournalEntry> all = journalEntryService.getAll();
        assertEquals(1, all.size());
        assertEquals("Test Entry", all.get(0).getTitle());
    }

    @Test
    public void testSaveEntryWithUser() {
        // Create user
        User user = new User();
        user.setUsername("soumyadeep");
        user.setPassword("1234");
        userService.saveNewUser(user);

        // Create entry
        JournalEntry entry = new JournalEntry();
        entry.setTitle("User Entry");
        entry.setContent("Linked to user");
        entry.setDate(LocalDateTime.now());

        journalEntryService.saveEntryTwoParameter(entry, "soumyadeep");

        User updatedUser = userService.findByUserName("soumyadeep");
        assertEquals(1, updatedUser.getJournalEntries().size());
        assertEquals("User Entry", updatedUser.getJournalEntries().get(0).getTitle());
    }

    @Test
    public void testDeleteJournalEntryId() {
        // Create user
        User user = new User();
        user.setUsername("deleteUser");
        user.setPassword("test");
        userService.saveNewUser(user);

        // Create entry
        JournalEntry entry = new JournalEntry();
        entry.setTitle("Delete Entry");
        entry.setContent("To be deleted");
        entry.setDate(LocalDateTime.now());

        journalEntryService.saveEntryTwoParameter(entry, "deleteUser");

        // Fetch user and entry ID
        User fetched = userService.findByUserName("deleteUser");
        ObjectId entryId = fetched.getJournalEntries().get(0).getId();

        // Delete
        journalEntryService.deleteJournalEntryId(entryId, "deleteUser");

        // Validate
        User updated = userService.findByUserName("deleteUser");
        assertTrue(updated.getJournalEntries().isEmpty());
        Optional<JournalEntry> deleted = journalEntryService.findById(entryId);
        assertFalse(deleted.isPresent());
    }

    @Test
    public void testFindById() {
        JournalEntry entry = new JournalEntry();
        entry.setTitle("Find Me");
        entry.setContent("I'm here!");
        entry.setDate(LocalDateTime.now());

        journalEntryService.saveEntry(entry);
        ObjectId id = entry.getId(); // assigned after save

        Optional<JournalEntry> found = journalEntryService.findById(id);
        assertTrue(found.isPresent());
        assertEquals("Find Me", found.get().getTitle());
    }

    @Test
    public void testGetAllEntries() {
        JournalEntry e1 = new JournalEntry();
        e1.setTitle("One");
        e1.setContent("Entry 1");
        e1.setDate(LocalDateTime.now());

        JournalEntry e2 = new JournalEntry();
        e2.setTitle("Two");
        e2.setContent("Entry 2");
        e2.setDate(LocalDateTime.now());

        journalEntryService.saveEntry(e1);
        journalEntryService.saveEntry(e2);

        List<JournalEntry> all = journalEntryService.getAll();
        assertEquals(2, all.size());
    }
}
