package ru.croc.vtb.wso2.api.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

/**
 * @author Logvin I. N.
 */
@EnableConfigurationProperties({
        TestsProperties.class
})
@SpringBootApplication
@ComponentScan(basePackages = {"ru.croc.vtb.wso2.api.tests", "ru.croc.vtb.wso2.api.tests.config", "ru.croc.vtb.wso2.api.tests.model", "ru.croc.vtb.wso2.api.tests.service"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
