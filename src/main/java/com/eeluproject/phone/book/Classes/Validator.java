package com.eeluproject.phone.book.Classes;

public class Validator {

  private boolean state;
  private String error;
  private String errorType;

  public Validator(boolean state, String error, String errorType) {
    this.state = state;
    this.error = error;
    this.errorType = errorType;
  }

  public boolean isState() {
    return this.state;
  }

  public boolean getState() {
    return this.state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

  public String getError() {
    return this.error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getErrorType() {
    return this.errorType;
  }

  public void setRrrorType(String errorType) {
    this.errorType = errorType;
  }
}
