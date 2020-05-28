package ru.croc.vtb.wso2.api.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

/**
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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
