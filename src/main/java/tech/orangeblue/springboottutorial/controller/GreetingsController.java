package tech.orangeblue.springboottutorial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface GreetingsController {

  public ResponseEntity getGreetingImage(@PathVariable String message);
}
