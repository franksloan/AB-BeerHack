package com.garagehack.response;

/**
 * @author Maxim Galushka
 */
public class Place {

  private String name;
  private String cuisine;
  private String address;
  private String phone;
  private double latitude;
  private double longitude;
  private String type;

  public Place(
    String name,
    String cuisine,
    String address,
    String phone,
    double latitude,
    double longitude,
    String type
  ) {
    this.name = name;
    this.cuisine = cuisine;
    this.address = address;
    this.phone = phone;
    this.latitude = latitude;
    this.longitude = longitude;
    this.type = type;
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Place{" +
      "name='" + name + '\'' +
      ", cuisine='" + cuisine + '\'' +
      ", address='" + address + '\'' +
      ", phone='" + phone + '\'' +
      ", latitude=" + latitude +
      ", longitude=" + longitude +
      ", type='" + type + '\'' +
      '}';
  }
}
