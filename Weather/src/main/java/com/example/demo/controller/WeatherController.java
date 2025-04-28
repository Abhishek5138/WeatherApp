package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Weather;

import com.example.demo.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    
    
    @GetMapping("/show")
    public String show() {
    	return"weather";
    }

    @PostMapping("/check")
//    @ResponseBody
    public String getWeather(@RequestParam(required = false) String city, Model model) {
        if (city != null && !city.isEmpty()) {
        	Weather weather=null;
        	
        	try {
             weather = weatherService.getWeather(city);
        		
        	}
        	catch (Exception e) {
				// TODO: handle exception
        		System.out.println("Write correct city");
        		
			}
            model.addAttribute("weather", weather);
        }
        return "weather"; // Return weather.html
    }
}


