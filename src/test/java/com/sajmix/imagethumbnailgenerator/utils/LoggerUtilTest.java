package com.sajmix.imagethumbnailgenerator.utils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class LoggerUtilTest {

  @InjectMocks
  private LoggerUtil loggerUtil;

  @Mock
  private Logger logger;

  public LoggerUtilTest() {
    super();
  }

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void logTest() {
    when(logger.isDebugEnabled()).thenReturn(true);
    LoggerUtil.log(logger, "message", "varArgs");
  }

  @Test
  public void logFalseDebugEnabledTest() {
    when(logger.isDebugEnabled()).thenReturn(false);
    LoggerUtil.log(logger, "message", "varArgs");
  }

  @Test
  public void logJsonTest() {
    when(logger.isDebugEnabled()).thenReturn(true);
    Object jsonObj = new Object();
    LoggerUtil.logJson(logger, "message", jsonObj);
  }

  @Test
  public void logJsonFalseDebugEnabledTest() {
    when(logger.isDebugEnabled()).thenReturn(false);
    Object jsonObj = new Object();
    LoggerUtil.logJson(logger, "message", jsonObj);
  }
}