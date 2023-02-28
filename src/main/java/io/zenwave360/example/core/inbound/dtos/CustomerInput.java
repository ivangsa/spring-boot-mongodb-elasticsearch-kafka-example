package io.zenwave360.example.core.inbound.dtos;

import io.zenwave360.example.core.domain.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import javax.validation.constraints.*;

/** */
public class CustomerInput implements Serializable {

  private String id;

  private Integer version;

  @NotNull
  @Size(min = 3, max = 250)
  private String firstName;

  @NotNull
  @Size(min = 3, max = 250)
  private String lastName;

  @NotNull
  @Size(min = 3, max = 250)
  private String password;

  @NotNull
  @Size(min = 3, max = 250)
  private String email;

  @NotNull
  @Size(min = 3, max = 250)
  private String username;

  public String getId() {
    return id;
  }

  public CustomerInput withId(String id) {
    this.id = id;
    return this;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public CustomerInput withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public CustomerInput withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public CustomerInput withPassword(String password) {
    this.password = password;
    return this;
  }

  public CustomerInput withEmail(String email) {
    this.email = email;
    return this;
  }

  public CustomerInput withUsername(String username) {
    this.username = username;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
