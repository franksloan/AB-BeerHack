package com.garagehack;

import com.garagehack.model.BeerDetails;
import com.garagehack.model.Beers;
import com.google.gson.Gson;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author Maxim Galushka
 */
public class BeerGarageClient {

  public static final Logger log = Logger.getLogger(BeerGarageClient.class);

  public static String USERNAME = "ab-11";
  public static String PASSWORD = "IGmVCnLuMj9gNWsF";

  public static String AUTH_TOKEN = "VNY7VOSwu3gEKw8M";

  private static final Map<String, List<BeerDetails>> cache = new HashMap<>();

  public static List<BeerDetails> queryBeerProfiles(
    String name,
    String zone,
    int offset
  )
  throws IOException {

    // caching slow beer details
    if (cache.containsKey(name)) {
      return cache.get(name);
    }

    boolean success = false;
    while (!success) {
      try {
        String details = queryBeer(
          AUTH_TOKEN,
          URLEncoder.encode(
            name,
            "UTF-8"
          ),
          zone,
          offset
        );
        Beers beers = beers(details);
        log.debug(
          String.format(
            "List of beers: [%s]",
            beers
          )
        );

        cache.put(name, beers.getBeers());
        return beers.getBeers();
      } catch (GarageAuthException authError) {
        try {
          // reset token
          log.error("Token invalid reset");
          AUTH_TOKEN = getAccessToken();
          log.debug(String.format("New token: [%s]", AUTH_TOKEN));
        } catch (Exception ex) {
          log.error("Cannot auth for 2nd time. Drop off.");
          success = true;
        }
      }
    }
    return Collections.emptyList();
  }


  public static String getAccessToken() throws IOException {
    String auth = "Basic " + Base64.getEncoder().encodeToString(
      String.format(
        "%s:%s",
        USERNAME,
        PASSWORD
      ).getBytes()
    );
    Content r =
      Request.Post("https://api.foodily.com/v1/token")
             .addHeader(new BasicHeader("Authorization", auth))
             .bodyForm(
               Form.form()
                   .add("grant_type", "client_credentials")
                   .build()
             )
             .execute().returnContent();
    String tokenResponse = r.asString();

    JSONObject json = new JSONObject(tokenResponse);
    String accessToken = json.getString("access_token");
    System.out.println(String.format("Access token: [%s]", accessToken));

    return accessToken;
  }

  public static Beers beers(String json) {
    Gson gson = new Gson();
    return gson.fromJson(json, Beers.class);
  }

  public static String queryBeer(
    String token,
    String term,
    String zone,
    int offset)
  throws IOException, GarageAuthException {
    Response response =
      Request.Get(
        String.format(
          "https://api.foodily" +
            ".com/v1/beerLookup?name=%s&zone=%s&limit=50&offset=%d",
          term,
          zone,
          offset
        )
      ).addHeader(
        new BasicHeader(
          "Authorization",
          String.format("Bearer %s", token)
        )
      ).execute();
    try {
      Content content = response.returnContent();
      return content.asString();
    } catch (HttpResponseException unauth) {
      throw new GarageAuthException("auth again");
    }
  }
}
