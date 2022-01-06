package com.shopify.assessment.common;

import com.shopify.assessment.models.Item;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Common functionalities used across the codebase.
 */
public class Common {
  /**
   * Generates random UUID for item ids.
   *
   * @return - item id.
   */
  public static String getRandomUUID() {
    return UUID.randomUUID().toString();
  }

  /**
   * Generates a standard Item related response.
   *
   * @param item - the item for which we are generating json response.
   * @return - {"item" : ItemObject}
   */
  public static Map<String, Item> standardItemResponse(Item item) {
    Map<String, Item> responseMap = new HashMap<>();
    responseMap.put("item", item);
    return responseMap;
  }

  /**
   * Generates a standard error related response.
   *
   * @param message - error message we want to show the user.
   * @return - {"message" : ErrorMessage}
   */
  public static Map<String, String> standardErrorResponse(String message) {
    Map<String, String> responseMap = new HashMap<>();
    responseMap.put("error_message", message);
    return responseMap;
  }

  /**
   * Generates a standard message response we want to show user.
   *
   * @param message - message we want to show user.
   * @return - {"message" : Message}
   */
  public static Map<String, String> standardMessageResponse(String message) {
    Map<String, String> responseMap = new HashMap<>();
    responseMap.put("message", message);
    return responseMap;
  }

  /**
   * Checks if String is null or empty.
   *
   * @param data - string data.
   * @return - true iff string is null or empty.
   */
  public static boolean isNullOrEmpty(String data) {
    return data == null || data.equals("");
  }

  /**
   * Checks if the Date is null.
   *
   * @param date - date we want to null check.
   * @return - true iff date is null.
   */
  public static boolean isNullOrEmpty(Date date) {
    return date == null;
  }
}
