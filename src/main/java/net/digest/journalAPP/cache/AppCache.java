package net.digest.journalAPP.cache;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.digest.journalAPP.entity.ConfigJournalAppEntity;
import net.digest.journalAPP.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.*;

@Slf4j
@Component
public class AppCache {

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    private Map<String, String> APP_CACHE = new HashMap<>();

    @PostConstruct
    public void init() {
        reload();
    }

    public void reload() {
        APP_CACHE.clear();
        configJournalAppRepository.findAll()
                .forEach(e -> APP_CACHE.put(e.getKey(), e.getValue()));
        log.info("✅ Cache Reloaded");
    }

    public String get(String key) {
        return APP_CACHE.get(key);
    }
}

