package com.example.cursework;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseWorkApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CourseWorkApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
