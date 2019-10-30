package com.forecast.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Weather {

    private static final double KELVIN_ZERO = -273.15D;
    private static final double NINE_FIFTH = 9/5D;
    private static final double FAHRENHEIT_DELTA = - 459.67D;

    private long date;
    private String cityName;
    private String countryCode;
    private String description;
    private double tempKelvin;
    private double tempCelsius;
    private double tempFahrenheit;
    private long sunrise;
    private long sunset;

    public Weather(long date, String cityName, String countryCode, String description, double tempKelvin,
                   long sunrise, long sunset) {
        this.date = date;
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.description = description;
        this.tempKelvin = tempKelvin;
        this.tempFahrenheit = fahrenheitFromKelvin(tempKelvin);
        this.tempCelsius = celsiusFromKelvin(tempKelvin);
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    private double celsiusFromKelvin(double kelvinTemp) {
        return roundForAtMost2Decimals(kelvinTemp + KELVIN_ZERO);
    }

    private double fahrenheitFromKelvin(double kelvinTemp) {
        return roundForAtMost2Decimals(kelvinTemp * NINE_FIFTH + FAHRENHEIT_DELTA);
    }

    private double roundForAtMost2Decimals(double value) {
        return Math.round(value * 100D) / 100D;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date=" + date +
                ", cityName='" + cityName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", description='" + description + '\'' +
                ", tempKelvin=" + tempKelvin +
                ", tempCelsius=" + tempCelsius +
                ", tempFahrenheit=" + tempFahrenheit +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }

    @JsonProperty("date")
    public long getDate() {
        return date;
    }

    public String getCityName() {
        return cityName;
    }

    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("overallDescription")
    public String getDescription() {
        return description;
    }

    @JsonProperty("sunrise")
    public long getSunrise() {
        return sunrise;
    }

    @JsonProperty("sunset")
    public long getSunset() {
        return sunset;
    }
    @JsonProperty("tempC")
    public double getTempCelsius() {
        return tempCelsius;
    }

    @JsonProperty("tempF")
    public double getTempFahrenheit() {
        return tempFahrenheit;
    }

    @JsonIgnore
    public double getTempKelvin() {
        return tempKelvin;
    }
}
