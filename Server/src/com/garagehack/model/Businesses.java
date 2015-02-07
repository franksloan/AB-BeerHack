package com.garagehack.model;

import com.garagehack.response.Place;

import java.util.*;

/**
 * @author Maxim Galushka
 */
public class Businesses {

  private Business[] businesses;

  @Override
  public String toString() {
    return "Businesses{" +
      "businesses=" + Arrays.toString(businesses) +
      '}';
  }

  public Business[] getBusinesses() {
    return businesses;
  }

  public void setBusinesses(Business[] businesses) {
    this.businesses = businesses;
  }

  public Businesses(Business[] businesses) {

    this.businesses = businesses;
  }

  public List<Place> places() {
    List<Place> places = new ArrayList<>();
    for (Business business : businesses) {
      StringJoiner joiner = new StringJoiner(",");

      places.add(
        new Place(
          business.getName(),
          business.getCategories()[0][0],
          business.getLocation().getCoordinate().getLatitude(),
          business.getLocation().getCoordinate().getLongitude()
        )
      );
    }

    return places;
  }
}
