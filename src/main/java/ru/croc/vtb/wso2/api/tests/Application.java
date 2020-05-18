package ru.croc.vtb.wso2.api.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import ru.croc.vtb.wso2.api.tests.config.ApplicationProperties;
import ru.croc.vtb.wso2.api.tests.config.Wso2ProxyConfig;

/**
 * @author Logvin I. N.
 */
@EnableAutoConfiguration
@EnableConfigurationProperties({
        ApplicationProperties.class
})
@Import({
       Wso2ProxyConfig.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
