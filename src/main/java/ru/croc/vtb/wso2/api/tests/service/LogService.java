package ru.croc.vtb.wso2.api.tests.service;

import net.schmizz.sshj.SSHClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Component
@Service
public interface LogService {
    SSHClient setupSsh() throws IOException;

    void downloadFile() throws IOException;

    void getErrorLog();
}
