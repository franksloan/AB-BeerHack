package com.garagehack;

import com.garagehack.model.Businesses;
import com.google.gson.Gson;

/**
 * @author Maxim Galushka
 */
public class QueryPlacesNearby {

  public static void main(String[] args) {
    YelpAPI yelp = new YelpAPI(
      YelpAPI.CONSUMER_KEY,
      YelpAPI.CONSUMER_SECRET,
      YelpAPI.TOKEN,
      YelpAPI.TOKEN_SECRET
    );

    // near our location
    String results = yelp.searchForBusinessesByLocation(
      "byob",
      "London",
      "51.5033630,-0.1276250"
    );

    Gson gson = new Gson();
    Businesses business = gson.fromJson(results, Businesses.class);

    System.out.println(business);
    System.out.println(business.places());
  }
}
