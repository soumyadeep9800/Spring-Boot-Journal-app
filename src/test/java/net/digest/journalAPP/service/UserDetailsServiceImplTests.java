package net.digest.journalAPP.service;

import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTests {

    //@Autowired
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;
    //@MockBean
    @Mock
    private UserRepository userRepository;

    @Test
    void loadUserByUsernameTest_success() {
        User user = new User();
        user.setUsername("ram");
        user.setPassword("uhec");
        user.setRole(List.of("USER"));

        when(userRepository.findByUsername("ram")).thenReturn(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername("ram");
        assertNotNull(userDetails);
        assertEquals("ram", userDetails.getUsername());
    }

    @Test
    void loadUserByUsernameTest_userNotFound() {
        when(userRepository.findByUsername("notfound")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("notfound");
        });
    }
}
