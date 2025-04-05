package com.abhishek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @GetMapping("/")
    public String index(final Model model) {
        // Add other attributes
        model.addAttribute("title", "This is SPRINGBOOT Application. running on Docker @5Apr2025");
        model.addAttribute("msg", "Application Is Deployed To Docker");

        // Add form object to the model (this could be a model object with name field)
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

    // Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        boolean isServiceHealthy = checkServiceHealth();
        
        if (isServiceHealthy) {
            return ResponseEntity.ok("Service is up and running!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Service is down!");
        }
    }

    // Logic to check the service health
    private boolean checkServiceHealth() {
        // Add logic to check actual service health, for example:
        // - Check database connection
        // - Check other dependencies
        // - Perform basic checks like disk space, memory usage, etc.

        // For now, let's assume the service is always healthy
        return true;  // Return false here to simulate a down service.
    }
}
