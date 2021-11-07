package tech.orangeblue.springboottutorial.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class PlatformObjectMapper {

  /** The objectmapper for jackson serialized objects. */
  private static ObjectMapper objectMapper = new ObjectMapper();

  static {
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
  }

  /** The Constant log. */
  private static final Logger log = LoggerFactory.getLogger(PlatformObjectMapper.class);

  /**
   * Instantiates a new platform object mapper.
   */
  private PlatformObjectMapper() {
  }

  /**
   * Map to object.
   *
   * @param <T>
   *          the generic type
   * @param jsonObject
   *          the json object
   * @param clazz
   *          the clazz
   * @return the t the platform exception
   * @throws IOException
   */

  public static <T> T mapToObject(Object jsonObject, Class<T> clazz) throws IOException {
    try {
      if (jsonObject instanceof Map) {
        log.debug("Checking for the object to be of type Map");
        return objectMapper.readValue(objectMapper.writeValueAsString(jsonObject), clazz);
      }
      return objectMapper.readValue(jsonObject.toString(), clazz);
    } catch (final IOException ex) {
      log.error("Exception occured during json parsing {}", ex);
      throw ex;
    }
  }

  /**
   * Read from file.
   *
   * @param path
   *          the path
   * @return the string
   * @throws IOException
   */
  public static String readFromFile(String path) {
    String inputParams = "";
    try (Scanner scanner = new Scanner(
        PlatformObjectMapper.class.getClassLoader().getResourceAsStream(path))) {
      log.debug("Scanning the file from the given path");
      inputParams = scanner.useDelimiter("\\A").next();
    }
    return inputParams;
  }

  /**
   * Map to json.
   *
   * @param jsonAsObject
   *          the json as object
   * @return the string
   * @throws Exception
   * @throws JsonProcessingException
   *           the platform exception
   */
  public static String mapToJson(Object jsonAsObject) {
    try {
      return objectMapper.writeValueAsString(jsonAsObject);
    } catch (JsonProcessingException e) {
      new Exception(e.getMessage());
    }
    return null;
  }

  public static <T> T mapJsonNodeToObject(JsonNode jsonNode, Class<T> clazz) {
    try {
      return objectMapper.treeToValue(jsonNode, clazz);
    } catch (JsonProcessingException e) {
      log.debug("Exception Occurred in mapJsonNodeToObject: {}", e);
      return null;
    }
  }

}
