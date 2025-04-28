package com.example.demo.entity;


import lombok.Data;


@Data
public class Weather {
	private String city;
    private String temp;
    private String humidity;
    private String description;
}
