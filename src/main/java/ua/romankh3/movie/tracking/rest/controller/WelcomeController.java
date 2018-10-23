package ua.romankh3.movie.tracking.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {

    @GetMapping
    public String getWelcomePage() {
        return "<h1>welcome to movieTracking</h1>";
    }

}
