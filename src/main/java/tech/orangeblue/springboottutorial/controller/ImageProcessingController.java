package tech.orangeblue.springboottutorial.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public interface ImageProcessingController {

  @PostMapping("/uploadImage")
  public Object uploadImage(MultipartFile[] file) throws IOException;

}
