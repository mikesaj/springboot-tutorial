package com.sajmix.imagethumbnailgenerator.controller;

import com.sajmix.imagethumbnailgenerator.model.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface GreetingsController {

  public ResponseEntity getGreetingImage(@PathVariable int id);
}
