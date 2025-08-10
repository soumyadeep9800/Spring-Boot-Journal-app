package net.digest.journalAPP.scheduler;

import net.digest.journalAPP.cache.AppCache;
import net.digest.journalAPP.entity.JournalEntry;
import net.digest.journalAPP.entity.User;
import net.digest.journalAPP.repository.UserRespositoryImpl;
import net.digest.journalAPP.service.EmailService;
import net.digest.journalAPP.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRespositoryImpl userRespository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    //@Scheduled(cron = "0 0 9 * * SUN")
    @Scheduled(cron = "0 * * ? * *")
    public void fetchUserAndSendSaMail(){
        List<User> users = userRespository.getUserForSA();
        for (User user: users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map( x -> x.getContent()).collect(Collectors.toList());
            String combinedContent = String.join(" ", filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(combinedContent);
            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days",sentiment);
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearCache(){
        appCache.init();
    }
}
