package com.garagehack.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * @author Maxim Galushka
 */
public class Location {

  @SerializedName("address")
  private String[] address;

  private Coordinate coordinate;

  @Override
  public String toString() {
    return "Location{" +
      "address=" + Arrays.toString(address) +
      '}';
  }

  public String[] getAddress() {
    return address;
  }

  public void setAddress(String[] address) {
    this.address = address;
  }

  public Location(String[] address) {

    this.address = address;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(Coordinate coordinate) {
    this.coordinate = coordinate;
  }
}
