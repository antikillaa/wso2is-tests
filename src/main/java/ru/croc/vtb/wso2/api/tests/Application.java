package ru.croc.vtb.wso2.api.tests;

import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import org.junit.runner.JUnitCore;
import org.junit.runners.model.InitializationError;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import static java.lang.System.exit;

/**
 * <b>Старт приложения</b>
 * <p>При запуске теста стартует автоматически, запускать самостоятельно только для ручного тестирования</p>
 *
 * @author Logvin I. N.
 */
@EnableConfigurationProperties({
        TestsProperties.class
})
@SpringBootApplication
@ComponentScan(basePackages = {"ru.croc.vtb.wso2.api.tests",
        "ru.croc.vtb.wso2.api.tests.config",
        "ru.croc.vtb.wso2.api.tests.model",
        "ru.croc.vtb.wso2.api.tests.service"
})
public class Application {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) throws InitializationError {
        SpringApplication.run(Application.class, args);
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        Cucumber test = new Cucumber(RunCucumberTestK3.class);
        new JUnitCore().run(test);
        exit(200);
    }
}
