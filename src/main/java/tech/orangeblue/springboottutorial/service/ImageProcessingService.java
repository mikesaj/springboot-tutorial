package tech.orangeblue.springboottutorial.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.orangeblue.springboottutorial.model.ImageModel;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public interface ImageProcessingService {

  public ImageModel generateThumbnail(MultipartFile file) throws IOException;
}
