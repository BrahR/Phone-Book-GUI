package com.eeluproject.phone.book.Classes;

import java.util.ArrayList;

public class User {

  public static ArrayList<User> users = new ArrayList<>();
  public String role = "user";
  private int id = 0;
  private String email;
  private String password;

  // private int count = 0;
  public User() {}

  public User(int id, String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public static User login(String email, String password) {
    for (User user : users) {
      System.out.println(user.role);
      if (
        email.equals(user.getEmail()) && password.equals(user.getPassword())
      ) {
        System.out.println("Logging in");
        return user;
      }
    }

    System.out.println("Invalid Email or Password");
    return null;
  }

  public static User register(int id, String email, String password) {
    User user = new User(id, email, password);
    System.out.println(users.size());
    users.add(user);
    System.out.println(users.size());

    return user;
  }

  public static void delete(String email) {
    users.removeIf(user -> user.getEmail().equals(email));
    BookedContact.contacts.removeIf(co -> co.getEmail().equals(email));
  }
}
