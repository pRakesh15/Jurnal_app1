package come.yiconic.start.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

  public void sendMail(String to,String subject,String body){
      try{
          SimpleMailMessage mailMessage=new SimpleMailMessage();
          mailMessage.setTo(to);
          mailMessage.setSubject(subject);
          mailMessage.setText(body);
      }catch (Exception e){
          log.error("Exepection while sendingMail"+e);
      }

  }
}
