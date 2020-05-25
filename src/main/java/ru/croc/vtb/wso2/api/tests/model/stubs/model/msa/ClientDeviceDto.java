package ru.croc.vtb.wso2.api.tests.model.stubs.model.msa;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Информация об устройстве клиента
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-07T09:52:39.537Z")

public class ClientDeviceDto {

    @JsonProperty("clientIP")
    private String clientIP = null;

    @JsonProperty("deviceName")
    private String deviceName = null;

    @JsonProperty("fingerprint")
    private String fingerprint = null;

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("mobileSdkData")
    private String mobileSdkData = null;

    @JsonProperty("userAgent")
    private String userAgent = null;

    public ClientDeviceDto clientIP(String clientIP) {
        this.clientIP = clientIP;
        return this;
    }

    /**
     * IP клиента
     *
     * @return clientIP
     **/
    @NotNull

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public ClientDeviceDto deviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    /**
     * Название устройства
     *
     * @return deviceName
     **/
    @NotNull

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public ClientDeviceDto fingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
        return this;
    }

    /**
     * Отпечаток устройства
     *
     * @return fingerprint
     **/
    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public ClientDeviceDto id(String id) {
        this.id = id;
        return this;
    }

    /**
     * ID устройства
     *
     * @return id
     **/
    @NotNull

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClientDeviceDto mobileSdkData(String mobileSdkData) {
        this.mobileSdkData = mobileSdkData;
        return this;
    }

    /**
     * Mobile-SDK информация
     *
     * @return mobileSdkData
     **/
    public String getMobileSdkData() {
        return mobileSdkData;
    }

    public void setMobileSdkData(String mobileSdkData) {
        this.mobileSdkData = mobileSdkData;
    }

    public ClientDeviceDto userAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * User агент
     *
     * @return userAgent
     **/
    @NotNull

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientDeviceDto clientDeviceDto = (ClientDeviceDto) o;
        return Objects.equals(this.clientIP, clientDeviceDto.clientIP) &&
                Objects.equals(this.deviceName, clientDeviceDto.deviceName) &&
                Objects.equals(this.fingerprint, clientDeviceDto.fingerprint) &&
                Objects.equals(this.id, clientDeviceDto.id) &&
                Objects.equals(this.mobileSdkData, clientDeviceDto.mobileSdkData) &&
                Objects.equals(this.userAgent, clientDeviceDto.userAgent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientIP, deviceName, fingerprint, id, mobileSdkData, userAgent);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ClientDeviceDto {\n");

        sb.append("    clientIP: ").append(toIndentedString(clientIP)).append("\n");
        sb.append("    deviceName: ").append(toIndentedString(deviceName)).append("\n");
        sb.append("    fingerprint: ").append(toIndentedString(fingerprint)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    mobileSdkData: ").append(toIndentedString(mobileSdkData)).append("\n");
        sb.append("    userAgent: ").append(toIndentedString(userAgent)).append("\n");
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

