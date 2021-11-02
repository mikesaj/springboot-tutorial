package com.sajmix.imagethumbnailgenerator.controller.impl;

import com.sajmix.imagethumbnailgenerator.controller.GreetingsController;
import com.sajmix.imagethumbnailgenerator.model.Image;
import com.sajmix.imagethumbnailgenerator.service.impl.GreetingsServiceImpl;
import com.sajmix.imagethumbnailgenerator.utils.LoggerUtil;
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

  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity getGreetingImage(@PathVariable int id) {

    LoggerUtil.log(LOGGER, "GreetingsControllerImpl API call to with data is {}", id);
    LOGGER.debug("GreetingsControllerImpl API call to with data is {}", id);

    Image image = new Image();

    image.setMimeType("image/png");
    image.setSourceUrl("./nicePic.png");
    image.setHeight(150);
    image.setWidth(135);
    image.setDescription("ouidygfihojsd");

    // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cause
    // description here");
    return ResponseEntity.status(HttpStatus.OK).body(image);
  }
}
