package net.digest.journalAPP.api.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.*;



@Getter
@Setter
public class WeatherResponse {
private Current current;
    @Getter@Setter
    public class Current{
        public int temperature;
        @JsonProperty("weather_descriptions")
        public ArrayList<String> weatherDescriptions;
        public int feelslike;
    }
}
