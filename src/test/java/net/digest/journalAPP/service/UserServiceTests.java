package net.digest.journalAPP.service;

import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        // Clean up all users after each test
        userRepository.deleteAll();
    }

    @Test
    public void testSaveNewUser() {
        User user = new User();
        user.setUsername("john");
        user.setPassword("password123");

        User savedUser = userService.saveNewUser(user);

        assertNotNull(savedUser.getId(), "Saved user should have an ID");
        assertEquals("john", savedUser.getUsername());
        assertNotEquals("password123", savedUser.getPassword(), "Password should be encoded");
        assertEquals(Arrays.asList("USER"), savedUser.getRole());
    }

    @Test
    public void testSaveNewAdmin() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("adminpass");

        User savedAdmin = userService.saveNewAdmin(user);

        assertEquals(Arrays.asList("USER", "ADMIN"), savedAdmin.getRole());
    }

    @Test
    public void testFindByUserName() {
        User user = new User();
        user.setUsername("ram");
        user.setPassword("ram123");

        userService.saveNewUser(user);

        User found = userService.findByUserName("ram");

        assertNotNull(found);
        assertEquals("ram", found.getUsername());
    }

    @Test
    public void testDeleteByUserName() {
        User user = new User();
        user.setUsername("deleteMe");
        user.setPassword("temp");

        userService.saveNewUser(user);
        userService.deleteByUserName("deleteMe");

        User deleted = userService.findByUserName("deleteMe");
        assertNull(deleted);
    }

    @Test
    public void testFindById() {
        User user = new User();
        user.setUsername("uniqueUser");
        user.setPassword("secure");

        User saved = userService.saveNewUser(user);
        Optional<User> found = userService.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("uniqueUser", found.get().getUsername());
    }

    @Test
    public void testGetAllUsers() {
        User u1 = new User();
        u1.setUsername("u1");
        u1.setPassword("p1");

        User u2 = new User();
        u2.setUsername("u2");
        u2.setPassword("p2");

        userService.saveNewUser(u1);
        userService.saveNewUser(u2);

        assertEquals(2, userService.getAll().size());
    }
}

