package tech.orangeblue.springboottutorial.service.impl;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.orangeblue.springboottutorial.service.VideoStreamingService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static tech.orangeblue.springboottutorial.constants.VideoConstants.*;

@Service
public class VideoStreamingServiceImpl implements VideoStreamingService {

  /** The Logger Reference. */
  private static final org.slf4j.Logger LOGGER = LoggerFactory
      .getLogger(VideoStreamingServiceImpl.class);

  public ResponseEntity<byte[]> getVideoContent(String videoID, String range) {

    byte[] data;
    long rangeBegin = 0, rangeEnd = 0;

    Map<String, String> videoPropertyMap = getVideoFileProperties(videoID);

    if (videoPropertyMap.isEmpty()) {
      LOGGER.error("video properties not found or incomplete");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    String videoFilePath = videoPropertyMap.get(VIDEO_PATH);
    String videoFileSize = videoPropertyMap.get(FILE_SIZE);
    String videoMimeType = videoPropertyMap.get(MIME_TYPE);

    long videoFileSizeL = Long.parseLong(videoFileSize);

    try {

      if (range == null) {
        return ResponseEntity.status(HttpStatus.OK).header(CONTENT_TYPE, videoMimeType)
            .header(CONTENT_LENGTH, String.valueOf(videoFileSizeL))
            .body(readByteRange(videoFilePath, rangeBegin, videoFileSizeL - 1));
      }

      String[] ranges = range.split("-");
      rangeBegin = Long.parseLong(ranges[0].substring(6));

      if (ranges.length > 1) {
        rangeEnd = Long.parseLong(ranges[1]);
      } else {
        rangeEnd = videoFileSizeL - 1;
      }
      if (videoFileSizeL < rangeEnd) {
        rangeEnd = videoFileSizeL - 1;
      }

      data = readByteRange(videoFilePath, rangeBegin, rangeEnd);

    } catch (IOException e) {
      LOGGER.error("IOException while trying to read videoID: {} \n {}", videoID, e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    String contentLength = String.valueOf((rangeEnd - rangeBegin) + 1);

    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).header(CONTENT_TYPE, videoMimeType)
        .header(ACCEPT_RANGES, BYTES).header(CONTENT_LENGTH, contentLength)
        .header(CONTENT_RANGE, BYTES + " " + rangeBegin + "-" + rangeEnd + "/" + videoFileSizeL)
        .body(data);
  }

  // In real life, this should fetch the data from a database
  private Map<String, String> getVideoFileProperties(String videoID) {

    HashMap<String, String> videoPropMap = new HashMap();

    if (videoID.equals("1")) {

      videoPropMap.put(VIDEO_PATH, "asset/video1.mp4");
      videoPropMap.put(FILE_SIZE, "7110465");
      videoPropMap.put(MIME_TYPE, "video/mp4");

    } else if (videoID.equals("2")) {

      videoPropMap.put(VIDEO_PATH, "asset/video2.mp4");
      videoPropMap.put(FILE_SIZE, "1499947");
      videoPropMap.put(MIME_TYPE, "video/mp4");

    } else {

      return Collections.emptyMap();
    }

    return videoPropMap;
  }

  private String getFilePath(String videoFilePath) throws IOException {

    URL url = this.getClass().getClassLoader().getResource(videoFilePath);

    if (url == null) {
      throw new IOException("File not found with path " + videoFilePath);
    }

    LOGGER.debug("Media File URL {}", url);

    return new File(url.getFile()).getAbsolutePath();
  }

  public byte[] readByteRange(String videoFilePath, long start, long end) throws IOException {

    Path path = Paths.get(getFilePath(videoFilePath));

    LOGGER.debug("readByteRange for {} from {} - {}", videoFilePath, start, end);

    try (InputStream inputStream = (Files.newInputStream(path));
        ByteArrayOutputStream bufferedOutputStream = new ByteArrayOutputStream()) {

      byte[] data = new byte[BYTE_RANGE];
      int nRead;

      while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
        bufferedOutputStream.write(data, 0, nRead);
      }
      bufferedOutputStream.flush();
      byte[] result = new byte[(int) (end - start) + 1];
      System.arraycopy(bufferedOutputStream.toByteArray(), (int) start, result, 0, result.length);
      return result;
    }
  }

}
