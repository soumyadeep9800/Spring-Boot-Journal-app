package net.digest.journalAPP.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    EmailService emailService;

    @Test
    void sendEmailTest(){
        emailService.sendEmail("habu32115@gmail.com","Testing Java mail sender","Hi! aap kaise hain ?");
    }
}
