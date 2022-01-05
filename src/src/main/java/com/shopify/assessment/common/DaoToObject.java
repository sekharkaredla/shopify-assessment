package com.shopify.assessment.common;

import com.shopify.assessment.dao.ItemDAO;
import com.shopify.assessment.models.Item;

public class DaoToObject {
  public static Item ItemDAOtoItem(ItemDAO itemDAO) {
    return new Item(itemDAO.getItemName(), itemDAO.getItemType(), itemDAO.getManufacturingCompany(),
        itemDAO.getManufacturingDate(), itemDAO.getTags());
  }
}
