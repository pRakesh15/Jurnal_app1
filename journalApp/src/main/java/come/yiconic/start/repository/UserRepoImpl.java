package come.yiconic.start.repository;

import come.yiconic.start.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepoImpl {

    //here we are going to write  the crite area ,mongo template and some advance querry.

//here i interducethe mongo template class
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<UserEntity> getUserForSA(){
        Query query=new Query();
//        query.addCriteria(Criteria.where("userName").is("rinku"));


        query.addCriteria(Criteria.where("email").exists(true));
        query.addCriteria(Criteria.where("email").ne(null).ne(""));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        //here is the beauty of orm is we are not directly use the collection
        //we just add the class and the orm autometically map the code to the db..
        return mongoTemplate.find(query, UserEntity.class);

    }
}
