package com.garagehack.model;

import java.util.List;

/**
 * @author Maxim Galushka
 */
public class Beers {

  private List<BeerDetails> beers;

  public Beers(List<BeerDetails> beers) {
    this.beers = beers;
  }

  public List<BeerDetails> getBeers() {
    return beers;
  }

  public void setBeers(List<BeerDetails> beers) {
    this.beers = beers;
  }

  @Override
  public String toString() {
    return "Beers{" +
      "beers=" + beers +
      '}';
  }
}
