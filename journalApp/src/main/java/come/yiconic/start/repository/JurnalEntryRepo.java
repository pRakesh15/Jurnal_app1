package come.yiconic.start.repository;


import come.yiconic.start.entity.JurnalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//here the repo play with the database
//here MongoRepository takes 2 parameters  1st one is the entity type and another one is
public interface JurnalEntryRepo extends MongoRepository<JurnalEntity, ObjectId> {

}
