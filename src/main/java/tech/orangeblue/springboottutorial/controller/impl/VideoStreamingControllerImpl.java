package tech.orangeblue.springboottutorial.controller.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tech.orangeblue.springboottutorial.controller.VideoStreamingController;
import tech.orangeblue.springboottutorial.service.impl.VideoStreamingServiceImpl;
import reactor.core.publisher.Mono;

// @RestController
@Controller
@RequestMapping("/video")
public class VideoStreamingControllerImpl implements VideoStreamingController {

  /** The Logger Reference. */
  private static final org.slf4j.Logger LOGGER = LoggerFactory
      .getLogger(VideoStreamingControllerImpl.class);

  @Autowired
  private VideoStreamingServiceImpl videoStreamService;

  @GetMapping("/stream/{videoID}")
  public Mono<ResponseEntity<byte[]>> streamVideo(@PathVariable("videoID") String videoID,
      @RequestHeader(value = "Range", required = false) String httpRangeList) {

    if (videoID == null || videoID.length() == 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "videoID cannot be null or empty");
    }

    LOGGER.debug("called video/stream/{} api endpoint ", videoID);
    return Mono.just(videoStreamService.getVideoContent(videoID, httpRangeList));
  }

  @GetMapping("/")
  public String videoPlayerView() {

    LOGGER.debug("called '/video' endpoint to display video with a player view (index.html) ");
    return "index";
  }

}
