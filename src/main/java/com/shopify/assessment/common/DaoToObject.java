package com.shopify.assessment.common;

import com.shopify.assessment.dao.ItemDAO;
import com.shopify.assessment.models.Item;

/**
 * The class used to convert user input (DAO) to internal Model(Item).
 */
public class DaoToObject {

  /**
   * This method verifies input.
   *
   * @param itemDAO - the input to be verified.
   */
  private static void verifyItemDAO(ItemDAO itemDAO) {
    if (Common.isNullOrEmpty(itemDAO.getItemName()) ||
        Common.isNullOrEmpty(itemDAO.getItemType()) ||
        Common.isNullOrEmpty(itemDAO.getManufacturingCompany()) ||
        Common.isNullOrEmpty(itemDAO.getManufacturingDate())) {
      throw new IllegalArgumentException("name, type, manufacturing date and company mandatory");
    }
  }

  /**
   * This is for adding a new Item.
   *
   * @param itemDAO - data to be added.
   * @return - new Item object generated.
   */
  public static Item itemDAOtoItem(ItemDAO itemDAO) {
    verifyItemDAO(itemDAO);
    return new Item(itemDAO.getItemName(), itemDAO.getItemType(),
        itemDAO.getManufacturingCompany(),
        itemDAO.getManufacturingDate(), itemDAO.getTags());
  }

  /**
   * This is for updating a new Item.
   *
   * @param itemDAO - data to be updated.
   * @param itemId - id of item to be updated.
   * @return - the Updated Item.
   */
  public static Item itemDAOtoItem(ItemDAO itemDAO, String itemId) {
    verifyItemDAO(itemDAO);
    return new Item(itemId, itemDAO.getItemName(), itemDAO.getItemType(),
        itemDAO.getManufacturingCompany(),
        itemDAO.getManufacturingDate(), itemDAO.getTags());
  }
}
