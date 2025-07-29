package net.digest.journalAPP.service;

import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //Implements UserDetailsService, which forces us to define the loadUserByUsername() method.
        User user = userRepository.findByUsername(username);
        if (user != null) { //Creates a UserDetails object using the UserBuilder from Spring Security.
            UserBuilder builder = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().toArray(new String[0]));

            return builder.build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}

