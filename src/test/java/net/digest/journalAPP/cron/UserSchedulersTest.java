package net.digest.journalAPP.cron;

import net.digest.journalAPP.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulersTest {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    void testFetchUsersAndSendSaMail(){
        userScheduler.fetchUserAndSendSaMail();
    }
}
