package com.eeluproject.phone.book.Classes;

public class Contact {

  private String name = "";
  private String phone = "";
  private String address;
  private String email;
  private int id = -1;

  public Contact() {}

  public Contact(
    String name,
    String phone,
    String address,
    String email,
    int id
  ) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.id = id;
  }

  public Contact(String email, String password) {
    System.out.println("two paras");
    setEmail(email);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
