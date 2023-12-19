package com.eeluproject.phone.book;

import com.eeluproject.phone.book.Classes.Admin;
import com.eeluproject.phone.book.GUI.LoginPanel;
import com.eeluproject.phone.book.Utils.CountryUtils;

public class Main {

  public static void main(String[] args) {
    Admin.register(69, "admin", "admin");
    // User.users.add();

    CountryUtils.populateCountryNames();
    LoginPanel.main(args);
  }
}
