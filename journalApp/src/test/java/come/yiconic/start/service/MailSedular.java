package come.yiconic.start.service;

import come.yiconic.start.scheduler.MailSendingScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailSedular {

    @Autowired
    private MailSendingScheduler mailSendingScheduler;

    @Test
    public  void testFetchUsersAnsSendMail(){
        mailSendingScheduler.fetchUserAndSendMail();
    }
}
