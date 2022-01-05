package com.shopify.assessment.common;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Common {
  public static String getRandomUUID() {
    return UUID.randomUUID().toString();
  }

  public static Map<String, Object> standardResponse(Object response, String id) {
    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("response", response);
    responseMap.put("id", id);
    return responseMap;
  }
}
