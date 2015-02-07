package com.garagehack;

import com.garagehack.model.BeerDetails;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author Maxim Galushka
 */
public class GetAllBeers {

  public static void main(String[] args) throws IOException {
    String[] zones = new String[]{"NAZ", "EUR", "LAN", "LAS", "APAC", "MEX"};
    for (String zone : zones) {
      for (int offset = 0; offset < 300; offset += 50) {
        List<BeerDetails> details = BeerGarageClient.queryBeerProfiles(
          "",
          zone,
          offset
        );
        for (BeerDetails b : details) {
          if (StringUtils.isNotBlank(b.getFlavorProfile())) {
            System.out.println(
              String.format(
                "%s\t%s\t%s",
                b.getName(),
                zone,
                b.getFlavorProfile()
              )
            );
          }
        }
      }
    }
  }
}
