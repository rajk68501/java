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

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    // This endpoint serves the index page
    @GetMapping("/")
    public String index(final Model model) {
        // Add other attributes for the view
        model.addAttribute("title", "This is a SpringBoot Static Web Application");
        model.addAttribute("msg", "Application Is Deployed To Kubernetes");

        // Add form object to the model (this could be a model object with name field)
        model.addAttribute("form", new MyForm());  // Passing form to the view
        
        return "index"; // Return the template (index.html)
    }

    // Define a form class with a name property (assuming form.name is used in the view)
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

    // HealthCheckController class to handle the /health endpoint for health checks
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

        // Simulated health check logic (you can replace this with real checks, e.g., DB connectivity)
        private boolean checkHealthCondition() {
            // For now, it returns true, indicating the application is healthy.
            return true; // Set to `true` to simulate a healthy service
        }
    }
}
