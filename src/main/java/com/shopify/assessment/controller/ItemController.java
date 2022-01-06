package com.shopify.assessment.controller;

import com.shopify.assessment.common.Common;
import com.shopify.assessment.dao.ItemDAO;
import com.shopify.assessment.models.Item;
import com.shopify.assessment.service.ItemService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

  @Autowired
  private ItemService itemService;

  /**
   * This method is an API for creating an item.
   *
   * @param itemBody - the item body.
   * @return - the response to user.
   */
  @PostMapping
  public ResponseEntity addItemDetails(@RequestBody ItemDAO itemBody) {
    try {
      Item addedItem = itemService.addItem(itemBody);
      return ResponseEntity.ok().body(Common.standardItemResponse(addedItem));
    } catch (Exception e) {
      return ResponseEntity.badRequest()
          .body(Common.standardErrorResponse("error : " + e.getMessage()));
    }
  }

  /**
   * This method is to get details of an item.
   *
   * @param itemId - id of item.
   * @return - the response to the user.
   */
  @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
  public ResponseEntity getDetails(@PathVariable("itemId") String itemId) {
    try {
      Item addedItem = itemService.getItem(itemId);
      return ResponseEntity.ok().body(Common.standardItemResponse(addedItem));
    } catch (IllegalArgumentException e) {
      return new ResponseEntity(Common.standardErrorResponse("item not found " + e.getMessage()),
          HttpStatus.NOT_FOUND);
    }
  }


  /**
   * This is the filter API. (The extra feature requested).
   *
   * @param name - name to filter.
   * @param type - type to filter.
   * @param company - manufacturing company to filter.
   * @param date - date of manufacturing to filter. (Only day is considered).
   * @param tags - tags for filtering items.
   * @return - list of filtered items to user.
   */
  @GetMapping
  public ResponseEntity filter(@RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "type", required = false) String type,
                                      @RequestParam(value = "company", required = false)
                                          String company,
                                      @RequestParam(value = "date", required = false) Date date,
                                      @RequestParam(value = "tags", required = false)
                                          List<String> tags) {
    try {
      List<Item> items =
          itemService.filterItems(Optional.ofNullable(name), Optional.ofNullable(type), Optional.ofNullable(company),
              Optional.ofNullable(date), tags);
      return ResponseEntity.ok().body(
          items.stream().map(Common::standardItemResponse).collect(Collectors.toList()));
    } catch (Exception e) {
      return new ResponseEntity(
          Common.standardErrorResponse("error while getting all items " + e.getMessage()),
          HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * This API is for deleting an item.
   *
   * @param itemId - id of item to be deleted.
   * @return - message saying deleted successfully or not.
   */
  @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
  public ResponseEntity deleteItem(@PathVariable("itemId") String itemId) {
    try {
      itemService.deleteItem(itemId);
      return ResponseEntity.ok().body(Common.standardMessageResponse("delete successful"));
    } catch (IllegalArgumentException e) {
      return new ResponseEntity(Common.standardErrorResponse("item not found " + e.getMessage()),
          HttpStatus.NOT_FOUND);
    }
  }

  /**
   * This API is for updating an item.
   *
   * @param itemId - id of item we want to update.
   * @param itemDAO - the item body we want to update.
   * @return - the updated item.
   */
  @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
  public ResponseEntity updateItem(@PathVariable("itemId") String itemId,
                                   @RequestBody ItemDAO itemDAO) {
    try {
      Item updatedItem = itemService.updateItem(itemDAO, itemId);
      return ResponseEntity.ok().body(Common.standardItemResponse(updatedItem));
    } catch (IllegalArgumentException e) {
      return new ResponseEntity(
          Common.standardErrorResponse("error while updating : " + e.getMessage()),
          HttpStatus.BAD_REQUEST);
    }
  }
}
