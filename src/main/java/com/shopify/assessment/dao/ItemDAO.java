package com.shopify.assessment.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

public class ItemDAO {

  @JsonProperty("item_name")
  private String itemName;
  @JsonProperty("item_type")
  private String itemType;
  @JsonProperty("manufacturing_company")
  private String manufacturingCompany;
  @JsonProperty("manufacturing_datetime")
  private Date manufacturingDate;
  @JsonProperty("tags")
  private List<String> tags;

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public String getItemType() {
    return itemType;
  }

  public void setItemType(String itemType) {
    this.itemType = itemType;
  }

  public String getManufacturingCompany() {
    return manufacturingCompany;
  }

  public void setManufacturingCompany(String manufacturingCompany) {
    this.manufacturingCompany = manufacturingCompany;
  }

  public Date getManufacturingDate() {
    return manufacturingDate;
  }

  public void setManufacturingDate(Date manufacturingDate) {
    this.manufacturingDate = manufacturingDate;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}
