package com.meli.forecasts.weather;

import com.meli.forecasts.weather.dto.coordinate.cartesian.SunCartesianCoordinate;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class WeatherForecastApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherForecastApplication.class, args);
	}

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello Far Away Solar System!";
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public SunCartesianCoordinate sunCartesianCoordinate() {
		return new SunCartesianCoordinate();
	}

}
