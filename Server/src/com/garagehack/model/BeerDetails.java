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
  private String abv;
  private String grains;

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

  public String getAbv() {
    return abv;
  }

  public void setAbv(String abv) {
    this.abv = abv;
  }

  public String getGrains() {
    return grains;
  }

  public void setGrains(String grains) {
    this.grains = grains;
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
