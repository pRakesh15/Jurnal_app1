package come.yiconic.start.scheduler;

import come.yiconic.start.entity.JurnalEntity;
import come.yiconic.start.entity.UserEntity;
import come.yiconic.start.repository.UserRepoImpl;
import come.yiconic.start.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailSendingScheduler {

    @Autowired
    private  UserRepoImpl userRepo;

    @Autowired
    private EmailService emailService;



//    @Scheduled(cron = "55 23 * * *")
   @Scheduled(cron = "0 * * ? * *")
    public void  fetchUserAndSendMail( ){
        List<UserEntity> users = userRepo.getUserForSA();

        for (UserEntity user: users) {
            List<JurnalEntity> jurnalEntities = user.getJurnalEntities();
            //add the filter for last 7 days journal for make the sentimental analysis

            //here we fetch the sentiment what is given by the ai


            //according to that  we will go to send email to the every user who enable sentiment message.
            emailService.sendMail(user.getEmail(),"Your Sentiments for this weeak ","Happy");
        }

    }



}
