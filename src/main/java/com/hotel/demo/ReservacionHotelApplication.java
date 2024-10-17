
  package com.hotel.demo;
  
  import org.springframework.boot.SpringApplication; 
  import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
  
  @SpringBootApplication
  @ComponentScan(basePackages="com.hotel.demo")
  //(exclude=SecurityAutoConfiguration.class)
  
  public class ReservacionHotelApplication {
  
  public static void main(String[] args) {
  SpringApplication.run(ReservacionHotelApplication.class, args); }
  
  }
 
