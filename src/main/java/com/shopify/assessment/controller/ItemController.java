package com.shopify.assessment.controller;

import com.shopify.assessment.common.Common;
import com.shopify.assessment.dao.ItemDAO;
import com.shopify.assessment.models.Item;
import com.shopify.assessment.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

  @Autowired
  private ItemService itemService;

  @PostMapping
  public ResponseEntity addItemDetails(@RequestBody ItemDAO itemBody) {
    try {
      Item addedItem = itemService.addItem(itemBody);
      return ResponseEntity.ok().body(Common.standardResponse(addedItem, addedItem.getId()));
    } catch (Exception e) {
      return ResponseEntity.badRequest()
          .body(Common.standardResponse("error : " + e.getMessage(), ""));
    }
  }

  @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
  public ResponseEntity getDetails(@PathVariable("itemId") String itemId) {
    try {
      Item addedItem = itemService.getItem(itemId);
      return ResponseEntity.ok().body(Common.standardResponse(addedItem, addedItem.getId()));
    } catch (IllegalArgumentException e) {
      return new ResponseEntity(Common.standardResponse("item not found " + itemId, ""),
          HttpStatus.NOT_FOUND);
    }
  }
}
