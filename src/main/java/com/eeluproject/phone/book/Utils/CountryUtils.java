package com.eeluproject.phone.book.Utils;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import java.util.ArrayList;

public class CountryUtils {

  private static String country = "EG";
  private static String countryCode = "20";
  public static ArrayList<String[]> countryNames = new ArrayList<>();
  public static int selectedIndex = 0;

  public static String getCountry() {
    return country;
  }

  public static void setCountry(int index) {
    country = countryNames.get(index)[0];
  }

  public static String getCountryAndCode() {
    return country + " (+" + countryCode + ")";
  }

  public static void setCountryCode(int index) {
    countryCode = countryNames.get(index)[1];
  }

  public static void populateCountryNames() {
    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    int i = 0;
    for (String region : phoneNumberUtil.getSupportedRegions()) {
      if (country == region) selectedIndex = i;

      int countryCode = phoneNumberUtil.getCountryCodeForRegion(region);
      countryNames.add(new String[] { region, String.valueOf(countryCode) });
      i++;
    }
  }
}
