package com.garagehack.response;

import com.garagehack.model.BeerDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxim Galushka
 */
public class Beer {
  private String name;
  private BeerDetails details;
  private List<Place> places = new ArrayList<>();

  public Beer(
    String name,
    BeerDetails details,
    List<Place> places
  ) {
    this.name = name;
    this.details = details;
    this.places = places;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BeerDetails getDetails() {
    return details;
  }

  public void setDetails(BeerDetails details) {
    this.details = details;
  }

  public List<Place> getPlaces() {
    return places;
  }

  public void setPlaces(List<Place> places) {
    this.places = places;
  }
}
