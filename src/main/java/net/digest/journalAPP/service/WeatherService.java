package net.digest.journalAPP.service;

import net.digest.journalAPP.api.response.WeatherResponse;
import net.digest.journalAPP.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String apiTemplate = appCache.get("weather_api");
        if (apiTemplate == null) throw new IllegalStateException("Config 'weather_api' not found in cache");

        String finalApi = apiTemplate
                .replace("<city>", city)
                .replace("<apiKey>", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
}

