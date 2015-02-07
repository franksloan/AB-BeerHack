package com.garagehack.model;

import java.util.List;

/**
 * @author Maxim Galushka
 */
public class BeerDetails {

  private String name;
  private List<String> countries;
  private String description;
  private String flavorProfile;

  public BeerDetails() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getCountries() {
    return countries;
  }

  public void setCountries(List<String> countries) {
    this.countries = countries;
  }

  public String getFlavorProfile() {
    return flavorProfile;
  }

  public void setFlavorProfile(String flavorProfile) {
    this.flavorProfile = flavorProfile;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "BeerDetails{" +
      "name='" + name + '\'' +
      ", countries=" + countries +
      ", flavorProfile='" + flavorProfile + '\'' +
      '}';
  }
}
