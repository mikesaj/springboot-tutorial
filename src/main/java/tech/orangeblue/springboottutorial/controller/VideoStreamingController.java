package tech.orangeblue.springboottutorial.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.http.server.ServerHttpResponse;
import reactor.core.publisher.Mono;

public interface VideoStreamingController {

  public Mono<ResponseEntity<byte[]>> streamVideo(String videoID, String httpRangeList);

  public String videoPlayerView();
}
