package ru.croc.vtb.wso2.api.tests.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * created by swagger.editor ExceptionObject
 *
 * edited by Logvin I.N.
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-15T12:59:08.011Z[GMT]")
public class ExceptionObject {
  @JsonProperty("message")
  private String message = null;

  @JsonProperty("exception")
  private String exception = null;

    public ExceptionObject() {
    }

    public ExceptionObject(Throwable exception) {
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
    }

    public ExceptionObject(String exception, String message) {
        this.exception = exception;
        this.message = message;
    }

  public ExceptionObject message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  **/

    public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ExceptionObject exception(String exception) {
    this.exception = exception;
    return this;
  }

  /**
   * Get exception
   * @return exception
  **/

    public String getException() {
    return exception;
  }

  public void setException(String exception) {
    this.exception = exception;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExceptionObject exceptionObject = (ExceptionObject) o;
    return Objects.equals(this.message, exceptionObject.message) &&
        Objects.equals(this.exception, exceptionObject.exception);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, exception);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExceptionObject {\n");

    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    exception: ").append(toIndentedString(exception)).append("\n");
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
