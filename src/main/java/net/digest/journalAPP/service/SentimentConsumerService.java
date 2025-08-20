package net.digest.journalAPP.service;

import net.digest.journalAPP.model.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class SentimentConsumerService {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "weekly-sentiments", groupId = "weekly-sentiment-group")
    public void consumer(SentimentData sentimentData){
        sendEmail(sentimentData);
    }

    private void sendEmail(SentimentData sentimentData){
        emailService.sendEmail(sentimentData.getEmail(), "Sentiment for previous week", sentimentData.getSentiment());
    }
}
