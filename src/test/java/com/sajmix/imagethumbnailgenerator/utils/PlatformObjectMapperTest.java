package com.sajmix.imagethumbnailgenerator.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PlatformObjectMapperTest {

  /** The Constant INVALID_JSON. */
  private static final String INVALID_JSON = "[{\"name\":\"john\",\"age\":22";

  /** The Constant JSON_OBJECT. */
  private static final String JSON_OBJECT = "[{\"name\":\"john\",\"age\":22}]";

  /** The Constant JSON_PATH. */
  private static final String JSON_PATH = "JsonForTest.json";

  /**
   * Test map to object with string.
   *
   * @throws IOException
   */
  @Test
  public void testMapToObjectWithString() throws IOException {
    final List<?> list = PlatformObjectMapper.mapToObject(JSON_OBJECT, List.class);
    Assert.assertNotNull(list);
    Assert.assertEquals(list.size(), 1);
  }

  /**
   * Test map to object with map.
   *
   * @throws IOException
   */
  @Test
  public void testMapToObjectWithMap() throws IOException {
    Map<String, Object> mapJsonObject = new HashMap<>();
    mapJsonObject.put("name", "john");
    mapJsonObject.put("age", 22);
    final Map<String, Object> map = PlatformObjectMapper.mapToObject(mapJsonObject, Map.class);
    Assert.assertNotNull(map);
    Assert.assertEquals(map.size(), 2);
  }

  /**
   * Test map to object Json failed.
   *
   * @throws IOException
   */
  @Test(expected = IOException.class)
  public void testMapToObjectJsonFailed() throws IOException {
    PlatformObjectMapper.mapToObject(INVALID_JSON, Map.class);
  }

  /**
   * Test read from file.
   *
   * @throws IOException
   *           Signals that an I/O exception has occurred.
   */
  @Test
  public void testReadFromFile() throws IOException {
    String jsonString = "{\"sectionTitle\": \"{sectionTitle}\",\"id\": \"{sectionId}\"}";
    String jsonObject = PlatformObjectMapper.readFromFile(JSON_PATH);
    assertEquals(jsonObject, jsonString);

  }

  @Test
  public void testMapToJson() throws IOException, Exception {
    assertNotNull(PlatformObjectMapper.mapToJson(INVALID_JSON));
  }

  @Test
  public void mapJsonNodeToObjectTest() throws IOException {
    JsonNode jsonNode = new TextNode("Hello WOrld");
    String res = PlatformObjectMapper.mapJsonNodeToObject(jsonNode, String.class);
    assertEquals(jsonNode.asText(), res);
  }
}