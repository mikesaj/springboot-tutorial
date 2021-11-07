package tech.orangeblue.springboottutorial.utils;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
  private List<String> allParams = new ArrayList<>();

  public void addParam(String key, String value) {
    allParams.add(key + "=" + value);
  }

  public String getQueryString() {
    return ("?" + String.join("&", allParams));
  }
}
