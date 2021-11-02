package com.sajmix.imagethumbnailgenerator.service.impl;

import com.sajmix.imagethumbnailgenerator.utils.PlatformObjectMapper;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;


public class GreetingsServiceImplTest {

  @InjectMocks
  GreetingsServiceImpl greetingsServiceImpl;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testSum() {

    int resultSum = greetingsServiceImpl.sum(1, 2);

    Assert.assertEquals(3, resultSum);
  }

//   @Test
//   public void multiply() {
//   }

  // @Test
  // public void divide() {
  // }
}