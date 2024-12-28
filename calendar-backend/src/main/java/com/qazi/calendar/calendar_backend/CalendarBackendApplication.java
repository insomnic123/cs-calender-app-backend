package com.qazi.calendar.calendar_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CalendarBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarBackendApplication.class, args);
	}

}
