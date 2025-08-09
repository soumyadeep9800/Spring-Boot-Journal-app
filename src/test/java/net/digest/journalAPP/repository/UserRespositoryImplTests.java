package net.digest.journalAPP.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRespositoryImplTests {
    @Autowired
    private UserRespositoryImpl userRespository;

    @Test
    public void testUserImpl(){
        userRespository.getUserForSA();
    }
}
