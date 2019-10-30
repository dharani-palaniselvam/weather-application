package com.forecast.api.owm;

import com.forecast.api.model.Weather;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class OpenWeatherMapClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherMapClient.class);

    private final OpenWeatherMapConfig config;
    private final RestTemplate restTemplate;

    public OpenWeatherMapClient(OpenWeatherMapConfig config, RestTemplateBuilder builder) {
        this.config = config;
        this.restTemplate = builder.build();
    }

    @Cacheable("weatherByCityId")
    public Weather fetchWeatherByCityId(String id) {
       
        ResponseEntity<String> weatherData = this.restTemplate
                .getForEntity(this.config.getWeatherUrl(), String.class, id, this.config.getAppkey());
        String jsonString = weatherData.getBody();
        LOGGER.info("Call to openweathermap API for cityId: {} returned: {}", id, jsonString);
        return createFromJsonString(jsonString);
    }

    
    private Weather createFromJsonString(String jsonString) {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
        Integer date = JsonPath.read(document, "$.dt");
        String cityName = JsonPath.read(document, "$.name");
        String countryCode = JsonPath.read(document, "$.sys.country");
        String description = JsonPath.read(document, "$.weather[0].description");
        Double temperature = JsonPath.read(document, "$.main.temp");
        Integer sunrise = JsonPath.read(document, "$.sys.sunrise");
        Integer sunset = JsonPath.read(document, "$.sys.sunset");
        return new Weather((long) date, cityName, countryCode, description, temperature, (long) sunrise, (long) sunset);
    }
}
