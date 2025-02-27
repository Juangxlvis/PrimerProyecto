package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
import jakarta.annotation.Generated;

/**
 * UserResponse
 */
@Getter
@Setter
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-20T21:07:46.519360700-05:00[America/Bogota]", comments = "Generator version: 7.7.0")
public class UserResponse {

  private String id;

  private String email;

  private String fullName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate dateBirth;

  private String role;

  private String password;

  public UserResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UserResponse(String id, String email, String fullName, String role, String password) {
    this.id = id;
    this.email = email;
    this.fullName = fullName;
    this.role = role;
    this.password = password;
  }

  public UserResponse id(String id) {
    this.id = id;
    return this;
  }

  @NotNull
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

    public UserResponse email(String email) {
    this.email = email;
    return this;
  }

  @NotNull
  @Schema(name = "email", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

    public UserResponse fullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  @NotNull
  @Schema(name = "fullName", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("fullName")
  public String getFullName() {
    return fullName;
  }

    public UserResponse dateBirth(LocalDate dateBirth) {
    this.dateBirth = dateBirth;
    return this;
  }

  @Valid
  @Schema(name = "dateBirth", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dateBirth")
  public LocalDate getDateBirth() {
    return dateBirth;
  }

    public UserResponse role(String role) {
    this.role = role;
    return this;
  }

  @NotNull
  @Schema(name = "role", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("role")
  public String getRole() {
    return role;
  }

    public UserResponse password(String password) {
    this.password = password;
    return this;
  }

  @NotNull
  @Schema(name = "password", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

    @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserResponse userResponse = (UserResponse) o;
    return Objects.equals(this.id, userResponse.id) &&
            Objects.equals(this.email, userResponse.email) &&
            Objects.equals(this.fullName, userResponse.fullName) &&
            Objects.equals(this.dateBirth, userResponse.dateBirth) &&
            Objects.equals(this.role, userResponse.role) &&
            Objects.equals(this.password, userResponse.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, fullName, dateBirth, role, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserResponse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
    sb.append("    dateBirth: ").append(toIndentedString(dateBirth)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
