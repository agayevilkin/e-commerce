package com.example.elcstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class EcommerceApplication {
    @GetMapping("/login")
    public String login() {
        return "loginpage";
    }

    @GetMapping("/token")
    public String getToken() {
        return "/tokenpage";
    }
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
