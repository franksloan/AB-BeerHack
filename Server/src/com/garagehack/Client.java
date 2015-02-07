package com.garagehack;

import com.garagehack.model.Beers;
import com.google.gson.Gson;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

/**
 * @author Maxim Galushka
 */
public class Client {

  public static String USERNAME = "ab-11";
  public static String PASSWORD = "IGmVCnLuMj9gNWsF";

  public static String AUTH_TOKEN = "VNX2SOSwxNMAJ934";

  public static void main(String[] args) throws IOException {

    //String accessToken = getAccessToken();
    //{"Authorization":"Bearer VNX3guSwu3gEKwyZ"}
    boolean success = false;
    while (!success) {
      try {
        String details = queryBeer(AUTH_TOKEN, "Budweiser");

        //System.out.println(stella.toString());
        System.out.println(beers(details));
        success = true;
      } catch (GarageAuthException authError) {
        try {
          // reset token
          AUTH_TOKEN = getAccessToken();
        } catch (Exception ex) {
          success = true;
        }
      }
    }
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

  public static String queryBeer(String token, String term)
  throws IOException, GarageAuthException {
    Response response =
      Request.Get(
        String.format(
          "https://api.foodily" +
            ".com/v1/beerLookup?name=%s&zone=EUR&limit=100" +
            "(name),sourceRecipe(url)),pairings(*))",
          term
        )
      ).addHeader(
        new BasicHeader(
          "Authorization",
          String.format("Bearer %s", token)
        )
      ).execute();
    Content content = response.returnContent();
    if (Content.NO_CONTENT.equals(content)) {
      throw new GarageAuthException("auth again");
    }

   return content.asString();
    //return new JSONObject(tokenResponse);

  }
}
