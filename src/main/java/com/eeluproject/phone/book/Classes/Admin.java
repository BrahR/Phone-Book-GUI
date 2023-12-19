package com.eeluproject.phone.book.Classes;

public class Admin extends User {

  public Admin(int id, String email, String password) {
    super(id, email, password);
    role = "admin";
  }

  public static User register(int id, String email, String password) {
    User user = new Admin(id, email, password);
    System.out.println(users.size());
    users.add(user);
    System.out.println(users.size());

    return user;
  }

  public static User findById(int id) {
    return User.users
      .stream()
      .filter(contact -> contact.getId() == id)
      .findFirst()
      .orElse(null);
  }

  public static User findByEmail(String email) {
    return User.users
      .stream()
      .filter(contact -> contact.getEmail().equals(email))
      .findFirst()
      .orElse(null);
  }

  public static int returnIdBy(String email) {
    User user = User.users
      .stream()
      .filter(contact -> contact.getEmail().equals(email))
      .findFirst()
      .orElse(null);

    return (user != null) ? user.getId() : -1;
  }
}
