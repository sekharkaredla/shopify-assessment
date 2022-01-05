package com.shopify.assessment.common;

import com.shopify.assessment.models.Item;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Common {
  public static String getRandomUUID() {
    return UUID.randomUUID().toString();
  }

  public static Map<String, Item> standardItemResponse(Item item) {
    Map<String, Item> responseMap = new HashMap<>();
    responseMap.put("item", item);
    return responseMap;
  }

  public static Map<String, String> standardErrorResponse(String message) {
    Map<String, String> responseMap = new HashMap<>();
    responseMap.put("error_message", message);
    return responseMap;
  }

  public static Map<String, String> standardMessageResponse(String message) {
    Map<String, String> responseMap = new HashMap<>();
    responseMap.put("message", message);
    return responseMap;
  }

  public static boolean isNullOrEmpty(String data) {
    return data == null || data.equals("");
  }

  public static boolean isNullOrEmpty(Date date) {
    return date == null;
  }
}
