package tech.orangeblue.springboottutorial.controller.impl;

import tech.orangeblue.springboottutorial.controller.ImageProcessingController;
import tech.orangeblue.springboottutorial.model.ImageModel;
import tech.orangeblue.springboottutorial.service.impl.ImageProcessingServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ImageProcessingControllerImpl implements ImageProcessingController {

  /** The Logger Reference. */
  private static final org.slf4j.Logger LOGGER = LoggerFactory
      .getLogger(ImageProcessingControllerImpl.class);

  @Autowired
  ImageProcessingServiceImpl imageProcessingService;

  @PostMapping("/uploadImage")
  public Object uploadImage(@RequestParam("image") MultipartFile[] files) throws IOException {

    LOGGER.debug("uploadImage API called");

    ArrayList<ImageModel> uploadRespList = new ArrayList();

    for (MultipartFile file : files) {

      if (file.isEmpty()) {
        continue;
      }

      ImageModel thumbnailRes = imageProcessingService.generateThumbnail(file);
      uploadRespList.add(thumbnailRes);
    }
    return uploadRespList;
  }

}
