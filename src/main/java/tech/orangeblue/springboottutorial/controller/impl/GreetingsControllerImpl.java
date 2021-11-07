package tech.orangeblue.springboottutorial.controller.impl;

import tech.orangeblue.springboottutorial.controller.GreetingsController;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("greetings")
public class GreetingsControllerImpl implements GreetingsController {

  /** The Logger Reference. */
  private static final org.slf4j.Logger LOGGER = LoggerFactory
      .getLogger(GreetingsControllerImpl.class);

  @GetMapping(value = "/{message}", produces = "application/json")
  public ResponseEntity<String> getGreetingImage(@PathVariable String message) {

    LOGGER.debug("GreetingsControllerImpl API call to with data is {}", message);

    if (message == null || message.length() == 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message cannot be null or empty");
    }
    return ResponseEntity.status(HttpStatus.OK).body("Greetings");
  }
}
