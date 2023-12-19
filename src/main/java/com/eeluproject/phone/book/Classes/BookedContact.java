package com.eeluproject.phone.book.Classes;

import com.eeluproject.phone.book.Utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;

public class BookedContact {

  public static ArrayList<Contact> contacts = new ArrayList<>();

  public BookedContact() {}

  public BookedContact(
    String name,
    String phone,
    String address,
    String email,
    int id
  ) {
    contacts.add(new Contact(name, phone, address, email, id));
  }

  public BookedContact(Contact contact) {
    contacts.add(contact);
  }

  public static Contact findById(int id) {
    return contacts
      .stream()
      .filter(contact -> contact.getId() == id)
      .findFirst()
      .orElse(null);
  }

  public static Contact findByEmail(String email) {
    return contacts
      .stream()
      .filter(contact -> contact.getEmail().equals(email))
      .findFirst()
      .orElse(null);
  }

  public static ArrayList<Contact> getBookedContactsByEmail(String email) {
    ArrayList<Contact> bookedContact = new ArrayList<>();
    for (Contact contact : getValidContacts()) {
      if (contact.getEmail().equals(email)) {
        bookedContact.add(contact);
      }
    }

    return bookedContact;
  }

  public static ArrayList<Contact> getValidContacts() {
    Iterator<Contact> contacts = BookedContact.contacts.iterator();
    ArrayList<Contact> validContacts = new ArrayList<>();

    while (contacts.hasNext()) {
      Contact contact = contacts.next();

      if (Utils.validatePhoneNumber(contact.getPhone())) {
        validContacts.add(contact);
      }
    }

    return validContacts;
  }

  public static boolean isDuplicated(int id, String phone) {
    return contacts
      .stream()
      .anyMatch(cont -> cont.getId() != id && cont.getPhone().equals(phone));
  }

  public static boolean isDuplicated(String phone) {
    return contacts.stream().anyMatch(cont -> cont.getPhone().equals(phone));
  }

  public static boolean idExists(int id) {
    return contacts.stream().anyMatch(contact -> contact.getId() == id);
  }

  public static boolean isEmailConnected(int id, String email) {
    return BookedContact.contacts
      .stream()
      .anyMatch(contact ->
        contact.getEmail().equals(email) && id == contact.getId()
      );
  }

  public static void deleteByPhone(String phnoe) {
    contacts.removeIf(contact -> contact.getPhone().equals(phnoe));
  }

  public static void deleteByEmail(String email) {
    contacts.removeIf(contact -> contact.getEmail().equals(email));
  }
}
