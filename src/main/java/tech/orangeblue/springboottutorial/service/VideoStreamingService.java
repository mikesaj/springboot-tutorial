package tech.orangeblue.springboottutorial.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface VideoStreamingService {

  public ResponseEntity<byte[]> getVideoContent(String videoID, String range);

  public byte[] readByteRange(String filename, long start, long end) throws IOException;

}
