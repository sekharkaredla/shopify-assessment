package com.shopify.assessment.service;

import com.shopify.assessment.common.DaoToObject;
import com.shopify.assessment.dao.ItemDAO;
import com.shopify.assessment.models.Item;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * This is the service class where item related logic is performed.
 *
 * <p>
 * Following actions are performed here:
 * - creating a new item
 * - storing the item in a map
 * - handling get call with itemId
 * - handling the filter call
 * - handling update (put NOT patch)
 * - handling delete
 * </p>
 * <p>
 * As of now we are using a ConcurrentHashMap as a data store. (In memory)
 * Later we can migrate this service to use a Spring JPA Repository.
 */
@Service
public class ItemService {
  private static final Logger logger = LogManager.getLogger(ItemService.class);
  private ConcurrentHashMap<String, Item> itemMap;
  private SimpleDateFormat fmt;

  public ItemService() {
    itemMap = new ConcurrentHashMap<>();
    fmt = new SimpleDateFormat("yyyyMMdd");
  }

  public Item addItem(ItemDAO itemDAO) {
    logger.info("Request to create item : " + itemDAO);
    Item newItem = DaoToObject.itemDAOtoItem(itemDAO);
    itemMap.put(newItem.getId(), newItem);
    logger.info("Created Item : " + newItem);
    return newItem;
  }

  public Item getItem(String itemId) {
    logger.info("Request to get item with id : " + itemId);
    if (!itemMap.containsKey(itemId)) {
      logger.error("invalid item id " + itemId);
      throw new IllegalArgumentException("item not found with id " + itemId);
    }
    return itemMap.get(itemId);
  }

  public List<Item> filterItems(Optional<String> name, Optional<String> type,
                                Optional<String> company,
                                Optional<Date> date, List<String> tags) {
    logger.info(
        String.format("filter request with filters %s %s %s %s %s", name, type, company, date,
            tags));
    List<Item> allItems = new ArrayList<>(itemMap.values());
    if (name.isPresent()) {
      allItems =
          allItems.stream().filter(eachItem -> eachItem.getItemName().equalsIgnoreCase(name.get()))
              .collect(Collectors.toList());
    }
    if (type.isPresent()) {
      allItems =
          allItems.stream().filter(eachItem -> eachItem.getItemType().equalsIgnoreCase(type.get()))
              .collect(Collectors.toList());
    }
    if (company.isPresent()) {
      allItems =
          allItems.stream().filter(
                  eachItem -> eachItem.getManufacturingCompany().equalsIgnoreCase(company.get()))
              .collect(Collectors.toList());
    }
    if (date.isPresent()) {
      allItems =
          allItems.stream().filter(eachItem -> fmt.format(eachItem.getManufacturingDate())
                  .equalsIgnoreCase(fmt.format(date.get())))
              .collect(Collectors.toList());
    }
    if (tags != null && tags.size() > 0) {
      allItems = allItems.stream().filter(eachItem -> {
        for (String eachTag : tags) {
          if (!eachItem.getTags().contains(eachTag)) {
            return false;
          }
        }
        return true;
      }).collect(Collectors.toList());
    }
    logger.info("sending response to user : " + allItems);
    return allItems;
  }

  public void deleteItem(String itemId) {
    logger.info("request to delete item " + itemId);
    if (!itemMap.containsKey(itemId)) {
      logger.error("invalid item id");
      throw new IllegalArgumentException("item not found with id " + itemId);
    }
    itemMap.remove(itemId);
  }

  public Item updateItem(ItemDAO itemDAO, String itemId) {
    logger.info(String.format("update request for item %s, %s", itemId, itemDAO));
    if (!itemMap.containsKey(itemId)) {
      logger.error("invalid item id " + itemId);
      throw new IllegalArgumentException("item not found with id " + itemId);
    }
    Item newItem = DaoToObject.itemDAOtoItem(itemDAO, itemId);
    itemMap.put(itemId, newItem);
    logger.info("response to user : " + itemDAO);
    return newItem;
  }
}
