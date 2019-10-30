package com.forecast.api.controller;

import com.forecast.api.model.Weather;
import com.forecast.api.owm.OpenWeatherMapClient;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "v1/weather")
public class WeatherController {

    private final OpenWeatherMapClient openWeatherMapClient;

    public WeatherController(OpenWeatherMapClient apiClient) {
        this.openWeatherMapClient = apiClient;
    }

    @GetMapping(value = "/city/{cityId}")
    public Weather getWeatherById(@PathVariable String cityId) {
        return openWeatherMapClient.fetchWeatherByCityId(cityId);
    }
}
