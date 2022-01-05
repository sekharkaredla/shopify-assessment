package com.shopify.assessment.service;

import com.shopify.assessment.common.DaoToObject;
import com.shopify.assessment.dao.ItemDAO;
import com.shopify.assessment.models.Item;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
  private ConcurrentHashMap<String, Item> itemMap;

  public ItemService() {
    itemMap = new ConcurrentHashMap<>();
  }

  public Item addItem(ItemDAO itemDAO) {
    Item newItem = DaoToObject.ItemDAOtoItem(itemDAO);
    itemMap.put(newItem.getId(), newItem);
    return newItem;
  }

  public Item getItem(String itemId) {
    if (!itemMap.containsKey(itemId)) {
      throw new IllegalArgumentException("item not found with id " + itemId);
    }
    return itemMap.get(itemId);
  }
}
