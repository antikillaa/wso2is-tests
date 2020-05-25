package ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.smsotp;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * <b>ДТО выходного тела ответа СМС-ОТП</b>
 *
 * @author Logvin I. N.
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-15T12:59:08.011Z[GMT]")
public class GetSmsOtpResponse {

    @JsonProperty("transactionId")
    private String transactionId = null;

    public GetSmsOtpResponse transactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    /**
     * Get transactionId
     *
     * @return transactionId
     **/
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetSmsOtpResponse getSmsOtpResponse = (GetSmsOtpResponse) o;
        return Objects.equals(this.transactionId, getSmsOtpResponse.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetSmsOtpResponse {\n");

        sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
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
