package com.garagehack.service;

import com.garagehack.response.Beer;
import com.google.gson.Gson;
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

/**
 * @author Maxim Galushka
 */
public class Service implements Container {

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
      headers(response);
      PrintStream body = null;
      body = response.getPrintStream();
      Beer beerResponse = new Beer(null, null, null);

      body.println(gson.toJson(beerResponse));
      body.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    Service instance = new Service();
    Server server = new ContainerServer(instance);
    Connection connection = new SocketConnection(server);

    SocketAddress address = new InetSocketAddress(8081);
    connection.connect(address);
  }
}
