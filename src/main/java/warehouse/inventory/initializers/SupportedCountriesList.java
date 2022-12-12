package warehouse.inventory.initializers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.experimental.UtilityClass;


/**
 * A class that contains a list of countries that are supported by the application.
 */
@UtilityClass
public class SupportedCountriesList {
  @Getter
  // Creating a new HashMap object and assigning it to the variable supportedCountries.
  private final Map<String, String> supportedCountries = new HashMap<>();
  @Getter
  // Creating a new ArrayList object and assigning it to the variable countries.
  private final List<String> countries = new ArrayList<>();

  /**
   * Returns true if the given country is supported by the application.
   *
   * @param country The country code of the country you want to check.
   * @return A boolean value.
   */
  public boolean isCountrySupported(String country) {
    return supportedCountries.containsKey(country);
  }
}