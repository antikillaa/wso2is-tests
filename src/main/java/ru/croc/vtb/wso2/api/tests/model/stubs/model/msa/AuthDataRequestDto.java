package ru.croc.vtb.wso2.api.tests.model.stubs.model.msa;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Информация для создания MSA сессии
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-07T09:52:39.537Z")

public class AuthDataRequestDto   {
  @JsonProperty("clientIP")
  private String clientIP = null;

  @JsonProperty("clientInfo")
  @Valid
  private Map<String, String> clientInfo = new HashMap<String, String>();

  @JsonProperty("deviceId")
  private String deviceId = null;

  @JsonProperty("deviceName")
  private String deviceName = null;

  @JsonProperty("identification")
  private IdentificationDto identification = null;

  @JsonProperty("inSessionId")
  private String inSessionId = null;

  public AuthDataRequestDto clientIP(String clientIP) {
    this.clientIP = clientIP;
    return this;
  }

  /**
   * IP клиента
   * @return clientIP
  **/
  @NotNull


  public String getClientIP() {
    return clientIP;
  }

  public void setClientIP(String clientIP) {
    this.clientIP = clientIP;
  }

  public AuthDataRequestDto clientInfo(Map<String, String> clientInfo) {
    this.clientInfo = clientInfo;
    return this;
  }

  public AuthDataRequestDto putClientInfoItem(String key, String clientInfoItem) {
    this.clientInfo.put(key, clientInfoItem);
    return this;
  }

  /**
   * Информация о клиенте
   * @return clientInfo
  **/
  @NotNull


  public Map<String, String> getClientInfo() {
    return clientInfo;
  }

  public void setClientInfo(Map<String, String> clientInfo) {
    this.clientInfo = clientInfo;
  }

  public AuthDataRequestDto deviceId(String deviceId) {
    this.deviceId = deviceId;
    return this;
  }

  /**
   * ID устройства клиента
   * @return deviceId
  **/
  @NotNull


  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public AuthDataRequestDto deviceName(String deviceName) {
    this.deviceName = deviceName;
    return this;
  }

  /**
   * Название устройства клиента
   * @return deviceName
  **/
  @NotNull


  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public AuthDataRequestDto identification(IdentificationDto identification) {
    this.identification = identification;
    return this;
  }

  /**
   * Username киента
   * @return identification
  **/
  @NotNull

  @Valid

  public IdentificationDto getIdentification() {
    return identification;
  }

  public void setIdentification(IdentificationDto identification) {
    this.identification = identification;
  }

  public AuthDataRequestDto inSessionId(String inSessionId) {
    this.inSessionId = inSessionId;
    return this;
  }

  /**
   * Сессия сервера интеграции
   * @return inSessionId
  **/
  @NotNull


  public String getInSessionId() {
    return inSessionId;
  }

  public void setInSessionId(String inSessionId) {
    this.inSessionId = inSessionId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthDataRequestDto authDataRequestDto = (AuthDataRequestDto) o;
    return Objects.equals(this.clientIP, authDataRequestDto.clientIP) &&
        Objects.equals(this.clientInfo, authDataRequestDto.clientInfo) &&
        Objects.equals(this.deviceId, authDataRequestDto.deviceId) &&
        Objects.equals(this.deviceName, authDataRequestDto.deviceName) &&
        Objects.equals(this.identification, authDataRequestDto.identification) &&
        Objects.equals(this.inSessionId, authDataRequestDto.inSessionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientIP, clientInfo, deviceId, deviceName, identification, inSessionId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthDataRequestDto {\n");

    sb.append("    clientIP: ").append(toIndentedString(clientIP)).append("\n");
    sb.append("    clientInfo: ").append(toIndentedString(clientInfo)).append("\n");
    sb.append("    deviceId: ").append(toIndentedString(deviceId)).append("\n");
    sb.append("    deviceName: ").append(toIndentedString(deviceName)).append("\n");
    sb.append("    identification: ").append(toIndentedString(identification)).append("\n");
    sb.append("    inSessionId: ").append(toIndentedString(inSessionId)).append("\n");
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

