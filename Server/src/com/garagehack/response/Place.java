package com.garagehack.response;

/**
 * @author Maxim Galushka
 */
public class Place {

  private String name;
  private String cuisine;
  private double latitude;
  private double longitude;

  public Place(String name, String cuisine, double latitude, double longitude) {
    this.name = name;
    this.cuisine = cuisine;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCuisine() {
    return cuisine;
  }

  public void setCuisine(String cuisine) {
    this.cuisine = cuisine;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
}
