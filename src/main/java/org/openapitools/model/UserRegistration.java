package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalDate;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UserRegistration
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-17T10:12:34.761348700-05:00[America/Bogota]", comments = "Generator version: 7.7.0")
public class UserRegistration {

  private String email;

  private String password;

  private String fullName;

  @Getter
  private String id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dateBirth;

  /**
   * Gets or Sets rol
   */
  public enum RolEnum {
    USER("user"),
    
    ADMIN("admin");

    private String value;

    RolEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RolEnum fromValue(String value) {
      for (RolEnum b : RolEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private RolEnum rol = RolEnum.USER;

  public UserRegistration() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UserRegistration(String email, String password, String fullName, LocalDate dateBirth, String rol, String id) {
    this.email = email;
    this.password = password;
    this.fullName = fullName;
    this.dateBirth = dateBirth;
    this.id = id;
  }

  public UserRegistration email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  @NotNull @Email
  @Schema(name = "email", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserRegistration password(String password) {
    this.password = password;
    return this;
  }

    public void setId(String id) {
    this.id = id;
  }

  /**
   * La contraseña del usuario (debe contener al menos un dígito, una mayúscula y una minúscula)
   * @return password
   */
  @NotNull @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).*$") @Size(min = 8) 
  @Schema(name = "password", description = "La contraseña del usuario (debe contener al menos un dígito, una mayúscula y una minúscula)", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserRegistration fullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  /**
   * Get fullName
   * @return fullName
   */
  @NotNull @Size(max = 100) 
  @Schema(name = "fullName", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("fullName")
  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public UserRegistration dateBirth(LocalDate dateBirth) {
    this.dateBirth = dateBirth;
    return this;
  }

  /**
   * Get dateBirth
   * @return dateBirth
   */
  @Valid 
  @Schema(name = "dateBirth", example = "Sun Dec 31 19:00:00 COT 1989", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dateBirth")
  public LocalDate getDateBirth() {
    return dateBirth;
  }

  public void setDateBirth(LocalDate dateBirth) {
    this.dateBirth = dateBirth;
  }

  public UserRegistration rol(RolEnum rol) {
    this.rol = rol;
    return this;
  }

  /**
   * Get rol
   * @return rol
   */
  
  @Schema(name = "rol", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("rol")
  public RolEnum getRol() {
    return rol;
  }

  public void setRol(RolEnum rol) {
    this.rol = rol;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserRegistration userRegistration = (UserRegistration) o;
    return Objects.equals(this.email, userRegistration.email) &&
        Objects.equals(this.password, userRegistration.password) &&
        Objects.equals(this.fullName, userRegistration.fullName) &&
        Objects.equals(this.dateBirth, userRegistration.dateBirth) &&
        Objects.equals(this.rol, userRegistration.rol);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password, fullName, dateBirth, rol);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserRegistration {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
    sb.append("    dateBirth: ").append(toIndentedString(dateBirth)).append("\n");
    sb.append("    rol: ").append(toIndentedString(rol)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

