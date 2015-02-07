package com.garagehack.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Maxim Galushka
 */
public class Business {
  private String name;

  @SerializedName("image_url")
  private String imageUrl;

  @SerializedName("snippet_text")
  private String description;

  private Location location;

  @SerializedName("display_phone")
  private String phone;

  private String[][] categories;



  @Override
  public String toString() {
    return "Business{" +
      "name='" + name + '\'' +
      ", imageUrl='" + imageUrl + '\'' +
      ", description='" + description + '\'' +
      ", location=" + location +
      ", phone='" + phone + '\'' +
      '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String[][] getCategories() {
    return categories;
  }

  public void setCategories(String[][] categories) {
    this.categories = categories;
  }

  public Business(
    String name,
    String imageUrl,
    String description,
    Location location,
    String phone,
    String[][] categories
  ) {
    this.name = name;
    this.imageUrl = imageUrl;
    this.description = description;
    this.location = location;
    this.phone = phone;
    this.categories = categories;
  }


}
