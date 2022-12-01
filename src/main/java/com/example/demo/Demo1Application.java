package com.example.demo;

import com.example.demo.Dto.CustomerRideCreateDto;
import com.example.demo.Entity.CustomerRide;
import com.example.demo.Entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.geo.Point;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {
		/*CustomerRideCreateDto d = new CustomerRideCreateDto();
		d.setFrom(new Point(3.5, 7.0));
		d.setTime(new Date(Time.valueOf(LocalTime.NOON).getTime()));
		d.setTo(new Point(5.5, 8.9));
		d.setUser_id(3L);
		d.setAmount(5);
		CustomerRide n = new ModelMapper().map(d, CustomerRide.class);
		User k = new User();
		n.setCustomer(k);
		System.out.println("From: " + n.getFrom()+ " To: " + n.getTo() +
				" Amount: " +n.getAmount() + " " + n.getCustomer() + " Date: " + n.getDate());*/
		SpringApplication.run(Demo1Application.class, args);
	}

}
