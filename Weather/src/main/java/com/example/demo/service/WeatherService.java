package com.example.demo.service;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Weather;

@Service
public class WeatherService {

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Weather getWeather(String city) {
        String url = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);

        JSONObject json = new JSONObject(response);
        JSONObject main = json.getJSONObject("main");
        JSONObject weatherJson = json.getJSONArray("weather").getJSONObject(0);

        Weather weather = new Weather();
        weather.setCity(city);
        weather.setTemp(main.get("temp").toString());
        weather.setHumidity(main.get("humidity").toString());
        weather.setDescription(weatherJson.getString("description"));

        return weather;
    }
}

