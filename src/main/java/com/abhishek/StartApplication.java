package com.abhishek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class StartApplication {

    @GetMapping("/")
    public String index(final Model model) {
        // Add other attributes
        model.addAttribute("title", "This is a SpringBoot Static Web Application");
        model.addAttribute("msg", "Application Is Deployed To Kubernetes");

        // Add form object to the model (this could be a model object with name field)
        model.addAttribute("form", new MyForm());  // Passing form to the view
        
        return "index"; // Return the template
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
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
}
