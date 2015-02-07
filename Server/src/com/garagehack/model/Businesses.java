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

      Set<String> cats = new TreeSet<>();
      for (int i = 0; i < business.getCategories().length; i++) {
        cats.addAll(Arrays.asList(business.getCategories()[i]));
      }

      for (String s : cats) {
        joiner.add(s.toLowerCase());
      }
      String categoriesList = joiner.toString();

      StringJoiner addressJoiner = new StringJoiner("\n");
      for (String addressBit : business.getLocation().getAddress()) {
        addressJoiner.add(addressBit);
      }

      places.add(
        new Place(
          business.getName(),
          business.getCategories()[0][0],
          addressJoiner.toString(),
          business.getPhone(),
          business.getLocation().getCoordinate().getLatitude(),
          business.getLocation().getCoordinate().getLongitude()
        )
      );
    }

    return places;
  }
}
