package ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * <b>ДТО входного тела авторизации по девайс-токену</b>
 *
 * @author Logvin I. N.
 */
@Validated
public class DeviceTokenAuthentication {

    @JsonProperty("deviceTokenId")
    private String deviceTokenId = null;

    @JsonProperty("challenge")
    private String challenge = null;

    @JsonProperty("secureCode")
    private String secureCode = null;

    public DeviceTokenAuthentication deviceTokenId(String deviceTokenId) {
        this.deviceTokenId = deviceTokenId;
        return this;
    }

    /**
     * Get deviceTokenId
     *
     * @return deviceTokenId
     **/
    public String getDeviceTokenId() {
        return deviceTokenId;
    }

    public void setDeviceTokenId(String deviceTokenId) {
        this.deviceTokenId = deviceTokenId;
    }

    public DeviceTokenAuthentication challenge(String challenge) {
        this.challenge = challenge;
        return this;
    }

    /**
     * Get domain
     *
     * @return domain
     **/
    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public DeviceTokenAuthentication secureCode(String secureCode) {
        this.secureCode = secureCode;
        return this;
    }

    /**
     * Get password
     *
     * @return password
     **/
    public String getSecureCode() {
        return secureCode;
    }

    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceTokenAuthentication DeviceTokenAuthentication = (DeviceTokenAuthentication) o;
        return Objects.equals(this.deviceTokenId, DeviceTokenAuthentication.deviceTokenId) &&
                Objects.equals(this.challenge, DeviceTokenAuthentication.challenge) &&
                Objects.equals(this.secureCode, DeviceTokenAuthentication.secureCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceTokenId, challenge, secureCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeviceTokenAuthentication {\n");

        sb.append("    deviceTokenId: ").append(toIndentedString(deviceTokenId)).append("\n");
        sb.append("    challenge: ").append(toIndentedString(challenge)).append("\n");
        sb.append("    secureCode: ").append(toIndentedString(secureCode)).append("\n");
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
