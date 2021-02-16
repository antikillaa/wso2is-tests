package ru.croc.vtb.wso2.api.tests;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import ru.croc.vtb.wso2.api.tests.impl.LogServiceImpl;
import ru.croc.vtb.wso2.api.tests.service.LogService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;
import static ru.croc.vtb.wso2.api.tests.impl.request.WSORequestServiceImpl.RESPONSE_BODY;

public class TestGetLogFile {


    LogService logService = new LogServiceImpl();

    @Test
    public void getErrorLog() throws IOException {
        Map<String, Object> header = new HashMap<>();
        header.put("Content-Type", "text/xml");
        header.put("Accept", "text/xml");


        ValidatableResponse r = given().log().everything(true)
                .headers(header)
                .body("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v=\"http://www.vtb24.ru/IT/Services/RB/AuthenticationService/v.1.0\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <v:FindEntry CorrelationId=\"?\">\n" +
                        "         <!--Optional:-->\n" +
                        "         <v:Request>\n" +
                        "            <v:UCN Value=\"11000035\"/>\n" +
                        "            <v:Domain Value=\"master\"/>\n" +
                        "         </v:Request>\n" +
                        " </v:FindEntry>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>")
                .post("http://test_ac:8080/services/AuthenticationServiceSoap")
                .then().log().all(true);
        XmlPath xmlPath = new XmlPath(r.extract().asString());
        RUN_CONTEXT.put(RESPONSE_BODY, r);
    }
}
