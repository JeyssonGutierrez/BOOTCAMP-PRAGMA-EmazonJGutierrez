package com.bootcamp_2024_1.emazon.domain.model;

import java.util.ArrayList;
import java.util.List;

public class DomainCategory {

  private Long id;
  private String name;
  private String description;
  private List<DomainCategory> subCategories;

  public DomainCategory() {
    this.subCategories = new ArrayList<>();
  }

  public DomainCategory(Long id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
