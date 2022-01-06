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
import org.springframework.stereotype.Service;

@Service
public class ItemService {
  private ConcurrentHashMap<String, Item> itemMap;
  private SimpleDateFormat fmt;

  public ItemService() {
    itemMap = new ConcurrentHashMap<>();
    fmt = new SimpleDateFormat("yyyyMMdd");
  }

  public Item addItem(ItemDAO itemDAO) {
    Item newItem = DaoToObject.itemDAOtoItem(itemDAO);
    itemMap.put(newItem.getId(), newItem);
    return newItem;
  }

  public Item getItem(String itemId) {
    if (!itemMap.containsKey(itemId)) {
      throw new IllegalArgumentException("item not found with id " + itemId);
    }
    return itemMap.get(itemId);
  }

  public List<Item> filterItems(Optional<String> name, Optional<String> type,
                                Optional<String> company,
                                Optional<Date> date, List<String> tags) {
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
    return allItems;
  }

  public void deleteItem(String itemId) {
    if (!itemMap.containsKey(itemId)) {
      throw new IllegalArgumentException("item not found with id " + itemId);
    }
    itemMap.remove(itemId);
  }

  public Item updateItem(ItemDAO itemDAO, String itemId) {
    if (!itemMap.containsKey(itemId)) {
      throw new IllegalArgumentException("item not found with id " + itemId);
    }
    Item newItem = DaoToObject.itemDAOtoItem(itemDAO, itemId);
    itemMap.put(itemId, newItem);
    return newItem;
  }
}
