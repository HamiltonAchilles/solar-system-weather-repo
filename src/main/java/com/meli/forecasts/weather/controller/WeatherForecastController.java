package com.meli.forecasts.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherForecastController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello Far Away Solar System!";
    }
}
