package com.sajmix.imagethumbnailgenerator.utils;

import org.slf4j.Logger;

/**
 * A utility class for consolidate all the logger checks in one place, hence
 * increasing the branch coverage.
 *
 */
public class LoggerUtil {

  /**
   * Method used for logging with 1 or more Objects
   *
   * @param logger
   * @param message
   * @param varArgs
   */
  public static void log(org.slf4j.Logger logger, String message, Object... varArgs) {

    if (logger.isDebugEnabled()) {
      logger.debug(message, varArgs);
    }
  }

  /**
   * Method used for logging a Json object. <br>
   * PlatformObjectMapper's mapToJson mapToJson method is called.
   *
   * @param logger
   * @param message
   * @param jsonObj
   */
  public static void logJson(org.slf4j.Logger logger, String message, Object jsonObj) {

    if (logger.isDebugEnabled()) {
      String jsonResponse = PlatformObjectMapper.mapToJson(jsonObj);
      logger.debug(message, jsonResponse);
    }
  }

}
