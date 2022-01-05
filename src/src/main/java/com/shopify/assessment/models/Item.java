package com.shopify.assessment.models;

import com.shopify.assessment.common.Common;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Item {
  private String id;
  private String itemName;
  private String itemType;
  private String manufacturingCompany;
  private Date manufacturingDate;
  private List<String> tags;

  public Item(String itemName, String itemType, String manufacturingCompany,
              Date manufacturingDate, List<String> tags) {
    this.id = Common.getRandomUUID();
    this.itemName = itemName;
    this.itemType = itemType;
    this.manufacturingCompany = manufacturingCompany;
    this.manufacturingDate = manufacturingDate;
    this.tags = tags;
  }

  public String getId() {
    return id;
  }

  public String getItemName() {
    return itemName;
  }

  public String getItemType() {
    return itemType;
  }

  public String getManufacturingCompany() {
    return manufacturingCompany;
  }

  public Date getManufacturingDate() {
    return manufacturingDate;
  }

  public List<String> getTags() {
    return tags;
  }

  @Override
  public String toString() {
    return "Item{" +
        "id='" + id + '\'' +
        ", itemName='" + itemName + '\'' +
        ", itemType='" + itemType + '\'' +
        ", manufacturingCompany='" + manufacturingCompany + '\'' +
        ", manufacturingDate=" + manufacturingDate +
        ", tags=" + tags +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return id.equals(item.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
