package com.shopify.assessment.common;

import com.shopify.assessment.dao.ItemDAO;
import com.shopify.assessment.models.Item;

public class DaoToObject {

  private static void verifyItemDAO(ItemDAO itemDAO) {
    if (Common.isNullOrEmpty(itemDAO.getItemName()) ||
        Common.isNullOrEmpty(itemDAO.getItemType()) ||
        Common.isNullOrEmpty(itemDAO.getManufacturingCompany()) ||
        Common.isNullOrEmpty(itemDAO.getManufacturingDate())) {
      throw new IllegalArgumentException("name, type, manufacturing date and company mandatory");
    }
  }

  public static Item itemDAOtoItem(ItemDAO itemDAO) {
    verifyItemDAO(itemDAO);
    return new Item(itemDAO.getItemName(), itemDAO.getItemType(),
        itemDAO.getManufacturingCompany(),
        itemDAO.getManufacturingDate(), itemDAO.getTags());
  }

  public static Item itemDAOtoItem(ItemDAO itemDAO, String itemId) {
    verifyItemDAO(itemDAO);
    return new Item(itemId, itemDAO.getItemName(), itemDAO.getItemType(),
        itemDAO.getManufacturingCompany(),
        itemDAO.getManufacturingDate(), itemDAO.getTags());
  }
}
