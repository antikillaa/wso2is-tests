package ru.croc.vtb.wso2.api.tests.model.stubs.model.msa;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * MSA сессия клиента
 */
@Validated

public class SessionDto   {
  @JsonProperty("clientDevice")
  private ClientDeviceDto clientDevice = null;

  @JsonProperty("clientInfo")
  @Valid
  private Map<String, String> clientInfo = new HashMap<String, String>();

  @JsonProperty("clientShardingInfo")
  private ClientShardingInfoDto clientShardingInfo = null;

  @JsonProperty("inSessionId")
  private String inSessionId = null;

  @JsonProperty("msaSessionId")
  private String msaSessionId = null;

  @JsonProperty("uniqueClientNumber")
  private String uniqueClientNumber = null;

  @JsonProperty("url")
  private String url = null;

  public SessionDto clientDevice(ClientDeviceDto clientDevice) {
    this.clientDevice = clientDevice;
    return this;
  }

  /**
   * Информация об устройстве клиента
   * @return clientDevice
  **/
  @NotNull

  @Valid

  public ClientDeviceDto getClientDevice() {
    return clientDevice;
  }

  public void setClientDevice(ClientDeviceDto clientDevice) {
    this.clientDevice = clientDevice;
  }

  public SessionDto clientInfo(Map<String, String> clientInfo) {
    this.clientInfo = clientInfo;
    return this;
  }

  public SessionDto putClientInfoItem(String key, String clientInfoItem) {
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

  public SessionDto clientShardingInfo(ClientShardingInfoDto clientShardingInfo) {
    this.clientShardingInfo = clientShardingInfo;
    return this;
  }

  /**
   * Информация о шарде клиента
   * @return clientShardingInfo
  **/
  @NotNull

  @Valid

  public ClientShardingInfoDto getClientShardingInfo() {
    return clientShardingInfo;
  }

  public void setClientShardingInfo(ClientShardingInfoDto clientShardingInfo) {
    this.clientShardingInfo = clientShardingInfo;
  }

  public SessionDto inSessionId(String inSessionId) {
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

  public SessionDto msaSessionId(String msaSessionId) {
    this.msaSessionId = msaSessionId;
    return this;
  }

  /**
   * ID сессии в MSA
   * @return msaSessionId
  **/
  @NotNull


  public String getMsaSessionId() {
    return msaSessionId;
  }

  public void setMsaSessionId(String msaSessionId) {
    this.msaSessionId = msaSessionId;
  }

  public SessionDto uniqueClientNumber(String uniqueClientNumber) {
    this.uniqueClientNumber = uniqueClientNumber;
    return this;
  }

  /**
   * УНК (уникальный номер клиента)
   * @return uniqueClientNumber
  **/
  @NotNull


  public String getUniqueClientNumber() {
    return uniqueClientNumber;
  }

  public void setUniqueClientNumber(String uniqueClientNumber) {
    this.uniqueClientNumber = uniqueClientNumber;
  }

  public SessionDto url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Опиши меня
   * @return url
  **/


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionDto sessionDto = (SessionDto) o;
    return Objects.equals(this.clientDevice, sessionDto.clientDevice) &&
        Objects.equals(this.clientInfo, sessionDto.clientInfo) &&
        Objects.equals(this.clientShardingInfo, sessionDto.clientShardingInfo) &&
        Objects.equals(this.inSessionId, sessionDto.inSessionId) &&
        Objects.equals(this.msaSessionId, sessionDto.msaSessionId) &&
        Objects.equals(this.uniqueClientNumber, sessionDto.uniqueClientNumber) &&
        Objects.equals(this.url, sessionDto.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientDevice, clientInfo, clientShardingInfo, inSessionId, msaSessionId, uniqueClientNumber, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionDto {\n");

    sb.append("    clientDevice: ").append(toIndentedString(clientDevice)).append("\n");
    sb.append("    clientInfo: ").append(toIndentedString(clientInfo)).append("\n");
    sb.append("    clientShardingInfo: ").append(toIndentedString(clientShardingInfo)).append("\n");
    sb.append("    inSessionId: ").append(toIndentedString(inSessionId)).append("\n");
    sb.append("    msaSessionId: ").append(toIndentedString(msaSessionId)).append("\n");
    sb.append("    uniqueClientNumber: ").append(toIndentedString(uniqueClientNumber)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

