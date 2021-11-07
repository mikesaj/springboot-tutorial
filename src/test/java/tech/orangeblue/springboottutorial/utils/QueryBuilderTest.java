package tech.orangeblue.springboottutorial.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class QueryBuilderTest {

  @InjectMocks
  private QueryBuilder queryBuilder;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void addParamTest() throws IOException {

    queryBuilder.addParam("name", "john");
    String queryString = queryBuilder.getQueryString();

    Assert.assertEquals("?name=john", queryString);

  }

  @Test
  public void getQueryStringTest() {
    List<String> param = Arrays.asList("name1", "john", "name2", "sam");
    ReflectionTestUtils.setField(queryBuilder, "allParams", param);

    String queryString = queryBuilder.getQueryString();

    Assert.assertEquals("?name1&john&name2&sam", queryString);

  }
}