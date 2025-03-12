package come.yiconic.start.scheduler;

import come.yiconic.start.entity.JurnalEntity;
import come.yiconic.start.entity.UserEntity;
import come.yiconic.start.enums.Sentiment;
import come.yiconic.start.repository.UserRepoImpl;
import come.yiconic.start.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MailSendingScheduler {

    @Autowired
    private  UserRepoImpl userRepo;

    @Autowired
    private EmailService emailService;



//    @Scheduled(cron = "55 23 * * *")
//   @Scheduled(cron = "0 * * ? * *")
    public void  fetchUserAndSendMail( ){
        List<UserEntity> users = userRepo.getUserForSA();

        for (UserEntity user: users) {
            System.out.println(user.getEmail());
            List<JurnalEntity> jurnalEntities = user.getJurnalEntities();
            List<Sentiment> sentiments = jurnalEntities.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer>sentimentCount=new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0) + 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCount.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if(mostFrequentSentiment !=null){
                emailService.sendMail(user.getEmail(),"Your Sentiments for this weak ",mostFrequentSentiment.toString());
            }

        }

    }



}
