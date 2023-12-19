package com.eeluproject.phone.book.Utils;

import com.eeluproject.phone.book.Classes.Admin;
import com.eeluproject.phone.book.Classes.BookedContact;
import com.eeluproject.phone.book.Classes.Validator;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Utils {

  public static Set<Integer> allocatedIds = new HashSet<>();

  public static boolean displayError(Validator validator) {
    boolean isValid = true;

    if (validator.getState()) return isValid;

    isValid = false;
    JOptionPane.showMessageDialog(
      null,
      validator.getError(),
      validator.getErrorType(),
      JOptionPane.WARNING_MESSAGE
    );

    return isValid;
  }

  public static boolean displayErrors(ArrayList<Validator> validators) {
    boolean isValid = true;

    for (Validator validator : validators) {
      boolean result = Utils.displayError(validator);
      if (!result) isValid = false;
    }

    return isValid;
  }

  public static boolean validatePhoneNumber(String phone) {
    if (phone.matches("[0-9]+")) {
      PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
      try {
        PhoneNumber parsedPhone = phoneUtil.parse(
          phone,
          CountryUtils.getCountry()
        );
        return phoneUtil.isValidNumber(parsedPhone);
      } catch (NumberParseException e) {
        System.out.println("Error parsing: " + e.getMessage());
      }
    }
    return false;
  }

  public static boolean _validPhone(String phone) {
    phone = phone.trim();
    if (phone.matches("^(01[0-2]|015)\\d{8}$")) {
      return true;
    }
    if (phone.length() != 11) {
      System.err.println("Phone number length must be 11");
    } else {
      System.err.println("Invalid Phone Number Format");
    }
    return false;
  }

  public static boolean validateName(String name) {
    name = name.trim();
    if (name.matches("[a-zA-Z ]*")) {
      return true;
    }
    System.err.println("Invalid Name, please enter a valid one");
    return false;
  }

  public static boolean validateAddress(String address) {
    // System.out.println("Invalid address, please Enter a valid one");
    return address.trim().matches(("^[a-zA-Z0-9.,\\s ]+$"));
  }

  public static boolean validateContact(String email) {
    return BookedContact.contacts
      .stream()
      .anyMatch(contact -> contact.getEmail().equals(email));
  }

  public static boolean validateEmail(String email) {
    return email.trim().matches(("^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"));
  }

  public static boolean validatePassword(String password) {
    return password.matches(
      "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"
    );
  }

  public static boolean isDuplicated(String phone) {
    return BookedContact.contacts
      .stream()
      .anyMatch(contact -> contact.getPhone().equals(phone));
  }
  public static boolean isDuplicated(int id, String phone) {
    return BookedContact.contacts
      .stream()
      .anyMatch(cont -> cont.getId() != id && cont.getPhone().equals(phone));
  }
  public static int generateRandomNumber() {
    int id = 0;
    do {
      id = (int) (Math.random() * Math.pow(10, 4));
    } while (Admin.findById(id) != null);

    return id;
  }

  public static void setValues(
    DefaultTableModel model,
    ArrayList<Object> data,
    int selectedRow
  ) {
    for (int i = 1; i < data.size(); i++) {
      model.setValueAt(data.get(i), selectedRow, i + 1);
    }
  }
}
