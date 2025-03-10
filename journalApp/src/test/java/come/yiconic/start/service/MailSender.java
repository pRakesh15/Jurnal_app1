package come.yiconic.start.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailSender {

    @Autowired EmailService emailService;

    @Test
    void testEmail(){
        emailService.sendMail("mryiconicgaming@gmail.com","helth check","hy ap kese ho");
    }


}
