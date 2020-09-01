package ru.croc.vtb.wso2.api.tests;

import org.junit.jupiter.api.Test;
import ru.croc.vtb.wso2.api.tests.impl.LogServiceImpl;
import ru.croc.vtb.wso2.api.tests.service.LogService;

import java.io.IOException;

public class TestGetLogFile {


    LogService logService = new LogServiceImpl();

    @Test
    public void getErrorLog() throws IOException {
        logService.downloadFile();
    }
}
