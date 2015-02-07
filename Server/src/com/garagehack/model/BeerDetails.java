package com.garagehack.model;

import java.util.List;

/**
 * @author Maxim Galushka
 */
public class BeerDetails {

  private String name;
  private List<String> countries;
  private String flavorProfile;

  public BeerDetails(String flavorProfile, List<String> countries, String name) {
    this.flavorProfile = flavorProfile;
    this.countries = countries;
    this.name = name;
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
}
