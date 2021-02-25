package com.meli.forecasts.weather;

import com.meli.forecasts.weather.model.SolarSystemDailyForecast;
import com.meli.forecasts.weather.service.SolarSystemDailyForecastService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@SpringBootApplication
public class WeatherForecastApplication {

	private SolarSystemDailyForecastService service;

	public WeatherForecastApplication(SolarSystemDailyForecastService service) {this.service = service;}

	public static void main(String[] args) {
		SpringApplication.run(WeatherForecastApplication.class, args);
	}

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello Far Away Solar System!";
	}

	@GetMapping("/forecasts")
	@ResponseBody
	List<SolarSystemDailyForecast> printForecasts() {
		List<SolarSystemDailyForecast> forecasts = service.generateForecasts();

		service.printForecasts(forecasts);

		return forecasts;
	}

}
