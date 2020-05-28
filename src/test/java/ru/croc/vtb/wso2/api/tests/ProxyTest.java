package ru.croc.vtb.wso2.api.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.service.ProxyTestHelper;

import java.io.IOException;

/**
 * <b>Конфигурация тестов</b>
 *
 * @author Logvin I. N.
 */
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableConfigurationProperties({
        TestsProperties.class
})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class ProxyTest {

    protected MockMvc mvc;

    @Autowired
    @Getter
    private TestsProperties testsProperties;

    @Getter
    private ProxyTestHelper testHelper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.testHelper = new ProxyTestHelper(this.testsProperties);
    }

    /**
     * <b>Мапит объект ДТО в JSON строку</b>
     * <p>Создан на будущее</p>
     *
     * @param obj дто модель
     * @return JSON строка дто модели
     * @throws JsonProcessingException
     */
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * <b>Мапит строку в объект ДТО</b>
     *
     * @param json  JSON строка ДТО
     * @param clazz класс ДТО
     * @param <T>
     * @return сконвертированный ДТО
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
