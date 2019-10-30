package com.forecast.api.owm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:owm.properties")
public class OpenWeatherMapConfig {

    @Value("${api.appKey}")
    private String appkey;

    @Value("${api.url.weather}")
    private String weatherUrl;

    public String getAppkey() {
        return appkey;
    }

    public String getWeatherUrl() {
        return weatherUrl;
    }
}
