package ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.smsotp;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * <b>ДТО входного тела СМС-ОТП</b>
 *
 * @author Logvin I. N.
 */
@Validated
public class GetSmsOtpRequest {

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("domain")
    private String domain = null;

    @JsonProperty("systemId")
    private String systemId = null;

    @JsonProperty("transactionType")
    private String transactionType = null;

    @JsonProperty("reuseIfActive")
    private Boolean reuseIfActive = null;

    public GetSmsOtpRequest id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GetSmsOtpRequest domain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * Get domain
     *
     * @return domain
     **/
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public GetSmsOtpRequest systemId(String systemId) {
        this.systemId = systemId;
        return this;
    }

    /**
     * Get systemId
     *
     * @return systemId
     **/
    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public GetSmsOtpRequest transactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    /**
     * Get transactionType
     *
     * @return transactionType
     **/
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public GetSmsOtpRequest reuseIfActive(Boolean reuseIfActive) {
        this.reuseIfActive = reuseIfActive;
        return this;
    }

    /**
     * Get reuseIfActive
     *
     * @return reuseIfActive
     **/
    public Boolean isReuseIfActive() {
        return reuseIfActive;
    }

    public void setReuseIfActive(Boolean reuseIfActive) {
        this.reuseIfActive = reuseIfActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetSmsOtpRequest getSmsOtpRequest = (GetSmsOtpRequest) o;
        return Objects.equals(this.id, getSmsOtpRequest.id) &&
                Objects.equals(this.domain, getSmsOtpRequest.domain) &&
                Objects.equals(this.systemId, getSmsOtpRequest.systemId) &&
                Objects.equals(this.transactionType, getSmsOtpRequest.transactionType) &&
                Objects.equals(this.reuseIfActive, getSmsOtpRequest.reuseIfActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, domain, systemId, transactionType, reuseIfActive);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetSmsOtpRequest {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    domain: ").append(toIndentedString(domain)).append("\n");
        sb.append("    systemId: ").append(toIndentedString(systemId)).append("\n");
        sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
        sb.append("    reuseIfActive: ").append(toIndentedString(reuseIfActive)).append("\n");
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
