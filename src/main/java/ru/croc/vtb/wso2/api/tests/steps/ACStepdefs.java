package ru.croc.vtb.wso2.api.tests.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.croc.vtb.wso2.api.tests.config.DbProperty;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.db.DbHelper;
import ru.croc.vtb.wso2.api.tests.impl.request.AcRequestServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.request.AcRequestService;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

@CucumberContextConfiguration
@EnableConfigurationProperties(value = TestsProperties.class)
public class ACStepdefs {
    AcRequestService acRequestService = new AcRequestServiceImpl();

    @Autowired
    @Getter
    private TestsProperties testsProperties;

    @Then("{string} Send Restore Password Request id: {string}")
    public void sendRestorePasswordSuccess(String env, String arg0) {
        acRequestService.sendRestorePasswordRequest(testsProperties, env, arg0);
    }

    @Then("{string} Send Change Password Success Request, id: {string} password: {string}")

    public void sendChangePasswordSuccessRequest(String env, String id, String password) {
        acRequestService.changePasswordRequest(env, id, password, testsProperties);
    }

    @Then("Send GetUcnByAliasAndPhoneAndDomain Request")
    public void sendGetUcnByAliasAndPhoneAndDomainRequestAliasPhone(DataTable dataTable) {
        Map<String, String> param = dataTable.asMaps().get(0);
        RUN_CONTEXT.put("par", param);
        acRequestService.GetUcnByAliasAndPhoneAndDomainRequest(param, testsProperties);
    }

    @Then("{string} Send DeviceToken Request id: {string}")
    public void sendDeviceTokenRequestId(String env, String arg0) {
        acRequestService.deviceTokenRequest(arg0, env, testsProperties);
    }

    @Then("Send authenticateByClientId Request")
    public void sendAuthenticateByClientIdRequestId(DataTable dataTable) {
        List<Map<Object, Object>> par = dataTable.asMaps(String.class, String.class);
        RUN_CONTEXT.put("par", par.get(0));
        acRequestService.authenticateByClientIdRequest(par.get(0), testsProperties);
    }

    @Then("Send GetUserDiscredited Request")
    public void sendGetUserDiscreditedRequestId(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        acRequestService.getUserDiscreditedRequest(param, testsProperties);
    }

    @Then("Send CheckRemotePasswordRestore Request")
    public void sendCheckRemotePasswordRestoreRequestId(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        acRequestService.getCheckRemotePasswordRestoreRequest(param, testsProperties);
    }


    @Then("{string} Send Add Guest Request")
    public void sendAddGuestRequest(String env) {
        acRequestService.sendAddGuestRequest(env, testsProperties);
    }

    @Then("Send User activateOrDeactivate request")
    public void sendUserActivateOrDeactivateRequest(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        acRequestService.sendUserActivateOrDeactivateRequest(param, testsProperties);
    }

    @Then("{string} Send Activated Request")
    public void sendActivatedRequest(String env) {
        acRequestService.sendActivatedRequest(env, testsProperties);
    }

    @Then("Send Get user Request")
    public void sendGetUserRequest(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        acRequestService.sendGetUserRequest(param, testsProperties);
    }

    @Then("Send Get Sms Otp Request")
    public void sendGetSmsOtpRequest(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        RUN_CONTEXT.put("getSmsOtp", param);
        acRequestService.sendGetSmsOtpRequest(param, testsProperties);
    }

    @Then("Send SmsOtp Request")
    public void sendSmsOtpRequest(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        acRequestService.sendSmsOtpRequest(param, testsProperties);
    }

    @Then("Send Static Password Request")
    public void sendStaticPasswordRequest(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        acRequestService.sendStaticPasswordRequest(param, testsProperties);
    }

    @Then("Get DB connection")
    public void getDBConnection(String arg0) {
        DbHelper conn = new DbHelper();
        Statement stmt = conn.getConnection(DbProperty.URL_db_k3, DbProperty.USER_k3, DbProperty.PASSWORD_k3);
        String URL = DbProperty.URL_ac_k3;
        System.out.println(stmt);

    }
}
