package ru.croc.vtb.wso2.api.tests.impl;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.croc.vtb.wso2.api.tests.service.LogService;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class LogServiceImpl implements LogService {
    private static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

    //    @Value("${test.host}")
    private final String host = "172.31.0.104";
    //    @Value("${test.username}")
    private final String username = "root";
    //    @Value("${test.password}")
    private final String password = "P@ssw0rdvtb0";
    //    @Value("${test.logpath}")
    private final String logpath = "/usr/lib64/wso2/wso2is/5.10.0/repository/logs/wso2carbon.log";

    private final String logDestination = "src/main/resources/log/";

    @Override
    public SSHClient setupSsh() throws IOException {
        SSHClient client = new SSHClient();
        client.addHostKeyVerifier(new PromiscuousVerifier());
        client.connect(host);
        client.authPassword(username, password);
        client.newSFTPClient();
        return client;
    }

    @Override
    public void downloadFile() throws IOException {
        SSHClient sshClient = setupSsh();
        SFTPClient sftpClient = sshClient.newSFTPClient();
        sftpClient.get(logpath, logDestination);
        sftpClient.close();
        sshClient.disconnect();
        getErrorLog();
    }

    @Override
    public void getErrorLog() {
        LocalDateTime now = LocalDateTime.now();
        String time = now.getHour() + ":" + now.getMinute();
        String fullLogDest = "src/main/resources/logs/wso2carbon.log";
        String errorLogDest = "src/main/resources/logs/errorLog.log";

        try {
            PrintWriter requests = new PrintWriter(errorLogDest);
            PrintWriter currentLog = null;

            Scanner s = new Scanner(new File(fullLogDest));
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.startsWith("TID:") && line.contains("ERROR") && line.contains(time))
                    currentLog = requests;
                else if (currentLog != null)
                    currentLog.println(line);
            }
            requests.close();
            s.close();
        } catch (IOException ioex) {
            log.error("File read error: " + ioex);
        }
    }
}
