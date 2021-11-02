package com.sajmix.imagethumbnailgenerator.service.impl;

import com.sajmix.imagethumbnailgenerator.service.GreetingsService;
import com.sajmix.imagethumbnailgenerator.utils.LoggerUtil;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GreetingsServiceImpl implements GreetingsService {

  /** The Logger Reference. */
  private static final org.slf4j.Logger LOGGER = LoggerFactory
      .getLogger(GreetingsServiceImpl.class);

  public int sum(int a, int b) {

    LoggerUtil.log(LOGGER, "GreetingsServiceImpl sum method called with data a: {} and b: {}", a,
        b);

    return a + b;
  }

  public int multiply(int a, int b) {
    return a + b;
  }

  public int divide(int a, int b) {
    return a + b;
  }
}
