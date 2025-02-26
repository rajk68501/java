package com.abhishek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    // This method has been removed, now you use constants for the values in your view
    // You can use APP_TITLE and APP_MSG in your Thymeleaf templates

    @GetMapping("/")
    public String index(final Model model) {
        // Use constants instead of hardcoding values
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
            // Simulate health check condition
            boolean isHealthy = checkHealthCondition();

            if (isHealthy) {
                return HttpStatus.OK; // Return HTTP 200 OK
            } else {
                return HttpStatus.SERVICE_UNAVAILABLE; // Return HTTP 503 Service Unavailable
            }
        }

        // Simulated health check logic (you can replace this with real checks)
        private boolean checkHealthCondition() {
            // Simulate the health of the service (for now, it's always false to return 503)
            return false; // Change to `true` to simulate a healthy service (returning 200)
        }
    }
}
