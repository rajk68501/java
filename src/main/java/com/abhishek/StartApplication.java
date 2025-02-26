package com.abhishek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@SpringBootApplication
@Controller
public class StartApplication {

    // Declare constants for the values
    public static final String APP_TITLE = "This is a SpringBoot Static Web Application";
    public static final String APP_MSG = "Application Is Deployed To Kubernetes";
    
    // Declare constant for health status
    public static final boolean IS_HEALTHY = false; // Change to 'true' to simulate a healthy service

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("title", APP_TITLE);
        model.addAttribute("msg", APP_MSG);
        model.addAttribute("form", new MyForm());  // Passing form to the view
        return "index"; // Return the template
    }

    // Define a form class with a name property (assuming form.name is used)
    public static class MyForm {
        private String name;

        // Getter and setter
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // HealthCheckController class to handle /health endpoint
    @RestController
    public static class HealthCheckController {

        @GetMapping("/health")
        public HttpStatus healthCheck() {
            // Use the constant to determine health status
            if (IS_HEALTHY) {
                return HttpStatus.OK; // Return HTTP 200 OK
            } else {
                return HttpStatus.SERVICE_UNAVAILABLE; // Return HTTP 503 Service Unavailable
            }
        }
    }
}
