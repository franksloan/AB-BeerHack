package com.garagehack.service;

import com.garagehack.BeerGarageClient;
import com.garagehack.YelpAPI;
import com.garagehack.model.BeerDetails;
import com.garagehack.model.Businesses;
import com.garagehack.response.Beer;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.Server;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.*;

/**
 * @author Maxim Galushka
 */
public class Service implements Container {

  public static final Logger log = Logger.getLogger(Service.class);

  // hack - default profile if not found
  private static final String DEFAULT_PROFILE = "fruity";

  private Map<String, List<String>> cousines = new HashMap<>();

  private final YelpAPI yelp;

  public Service() {
    yelp = new YelpAPI(
      YelpAPI.CONSUMER_KEY,
      YelpAPI.CONSUMER_SECRET,
      YelpAPI.TOKEN,
      YelpAPI.TOKEN_SECRET
    );

    cousines.put(
      "citrus_zesty",
      Arrays.asList("caribbean", "indpak", "russian", "turkish")
    );
    cousines.put(
      "fruity",
      Arrays.asList(
        "fishnchips",
        "indpak",
        "persian",
        "thai",
        "vietnamese"
      )
    );
    cousines.put(
      "green_hoppy",
      Arrays.asList("bbq", "caribbean", "turkish")
    );
    cousines.put(
      "roasted_toasted",
      Arrays.asList("bbq", "indpak", "moroccan", "turkish")
    );
    cousines.put(
      "spicy",
      Arrays.asList("indpak", "turkish")
    );
    cousines.put(
      "toffee_caramel",
      Arrays.asList(
        "caribbean",
        "indpak",
        "persian",
        "russian",
        "turkish"
      )
    );

    cousines.put(
      "cider",
      Arrays.asList(
        "caribbean",
        "moroccan",
        "turkish"
      )
    );

    cousines.put(
      "floral",
      Arrays.asList(
        "bbq",
        "fishnchips",
        "indpack",
        "persian",
        "thai",
        "vietnamese"
      )
    );

  }

  public void headers(Response response) {
    long time = System.currentTimeMillis();
    response.setValue("Content-Type", "application/json");
    response.setValue("Server", "Beer");
    response.setDate("Date", time);
    response.setDate("Last-Modified", time);
    response.setValue("Access-Control-Allow-Origin", "*");
  }

  @Override
  public void handle(
    Request request, Response response
  ) {
    try {
      Gson gson = new Gson();

      String name = request.getParameter("name");
      name = StringUtils.isNotBlank(name) ? name : "Budweiser";
      log.debug(String.format("Name = [%s]", name));

      String location = request.getParameter("location");
      location = StringUtils.isNotBlank(location) ? location :
        "51.5033630,-0.1276250";
      log.debug(String.format("Location = [%s]", location));

      //String[] coordinates = location.split(",");
      //double latitude = Double.parseDouble(coordinates[0]);
      //double longitude = Double.parseDouble(coordinates[1]);

      List<BeerDetails> details = BeerGarageClient.queryBeerProfiles(
        name,
        "EUR",
        0
      );
      BeerDetails beerDetails = details.isEmpty() ? new BeerDetails() :
        details.get(
          0
        );

      String profile = beerDetails.getFlavorProfile();
      if(profile == null){
        beerDetails.setFlavorProfile(DEFAULT_PROFILE);
      }

      List<String> filterCategories = (profile !=
        null) ? cousines.get(profile) : cousines.get(DEFAULT_PROFILE);

      StringJoiner joiner = new StringJoiner(",");
      if (filterCategories != null) {
        for (String cats : filterCategories) {
          joiner.add(cats);
        }
      }

      String yelpPlaces = yelp.searchForBusinessesByLocation(
        "byob",
        "London",
        location,
        joiner.toString()
      );
      Businesses business = gson.fromJson(yelpPlaces, Businesses.class);
      business = (business == null) ? new Businesses() : business;

      headers(response);
      PrintStream body = response.getPrintStream();
      Beer beerResponse = new Beer(
        name,
        beerDetails,
        business.places()
      );

      body.println(gson.toJson(beerResponse));
      body.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    Service instance = new Service();
    Server server = new ContainerServer(instance);
    Connection connection = new SocketConnection(server);

    SocketAddress address = new InetSocketAddress(8081);
    connection.connect(address);

    log.debug("Service started");
  }
}
