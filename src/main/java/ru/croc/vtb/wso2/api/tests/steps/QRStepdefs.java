package ru.croc.vtb.wso2.api.tests.steps;

import io.cucumber.java.en.Then;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.request.QRRequestServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.request.QRRequestService;

public class QRStepdefs {
    QRRequestService qrRequestService = new QRRequestServiceImpl();
    @Autowired
    @Getter
    private TestsProperties testsProperties;

    @Then("{string} Send generate QR Request")
    public void sendGenerateQRRequest(String env) {
        qrRequestService.generateQR(env, testsProperties);
    }

    @Then("{string} Send verify QR Request")
    public void sendVerifyQRRequest(String env) {
        qrRequestService.verifyQR(env, testsProperties);
    }

    @Then("{string} Send approve QR Request")
    public void sendApproveQRRequest(String env) {
        qrRequestService.approveQR(env, testsProperties);
    }

    @Then("{string} Send get QR status Request")
    public void sendGetQRStatusRequest(String env) {
        qrRequestService.getQRStatusRequest(env, testsProperties);
    }
}
