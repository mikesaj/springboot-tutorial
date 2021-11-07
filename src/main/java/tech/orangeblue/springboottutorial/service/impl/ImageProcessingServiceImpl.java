package tech.orangeblue.springboottutorial.service.impl;

import tech.orangeblue.springboottutorial.controller.impl.ImageProcessingControllerImpl;
import tech.orangeblue.springboottutorial.service.ImageProcessingService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import tech.orangeblue.springboottutorial.model.ImageModel;

@Service
public class ImageProcessingServiceImpl implements ImageProcessingService {

  private static final int IMG_WIDTH = 802;
  private static final int IMG_HEIGHT = 458;

  /** The Logger Reference. */
  private static final org.slf4j.Logger LOGGER = LoggerFactory
      .getLogger(ImageProcessingControllerImpl.class);

  public ImageModel generateThumbnail(MultipartFile file) throws IOException {

    // get filename & extension
    String fileName = "";
    if (file.getOriginalFilename() != null) {
      fileName = file.getOriginalFilename().toString();
    }

    String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

    boolean isImageFormatValid = validateImageFileFormat(fileExtension);

    if (!isImageFormatValid) {
      return null;
    }

    InputStream inputStream = file.getInputStream();
    BufferedImage bufferedImage = ImageIO.read(inputStream);

    int currentHeight = bufferedImage.getHeight();
    int currentWidth = bufferedImage.getWidth();

    LOGGER.debug("Resize image with dimension {}X{} into {}X{}", currentWidth, currentHeight,
        IMG_WIDTH, IMG_HEIGHT);

    // resize image
    Image scaledImage = bufferedImage.getScaledInstance(IMG_WIDTH, IMG_HEIGHT,
        Image.SCALE_REPLICATE);

    // save the resize image aka thumbnail
    ImageIO.write(convertToBufferedImage(scaledImage), "png", new File(fileName));
    LOGGER.debug("Image saved into /{}", fileName);

    FileNameMap fileNameMap = URLConnection.getFileNameMap();
    String mimeType = fileNameMap.getContentTypeFor(fileName);

    // Generate imageModel response
    ImageModel imageModel = new ImageModel();

    imageModel.setHeight(IMG_HEIGHT);
    imageModel.setWidth(IMG_WIDTH);
    imageModel.setMimeType(mimeType);
    imageModel.setSourceUrl("./" + fileName);
    imageModel.setDescription("thumbnail");

    return imageModel;
  }

  /**
   * Check if file has a supported image format
   */
  private boolean validateImageFileFormat(String fileExtension) {

    fileExtension = fileExtension.toLowerCase();

    String[] writerNames = ImageIO.getWriterFormatNames();

    // Arrays.stream(writerNames).forEach(System.out::println);

    return Arrays.asList(writerNames).contains(fileExtension);
  }

  private static BufferedImage convertToBufferedImage(Image img) {

    Map<RenderingHints.Key, Object> hints = new HashMap<>();

    if (img instanceof BufferedImage) {
      return (BufferedImage) img;
    }

    // Create a buffered image with transparency
    BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null),
        BufferedImage.TYPE_INT_ARGB);

    Graphics2D graphics2D = bufferedImage.createGraphics();

    hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION,
        RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

    graphics2D.setRenderingHints(hints);

    graphics2D.drawImage(img, 0, 0, null);
    graphics2D.dispose();

    return bufferedImage;
  }
}
