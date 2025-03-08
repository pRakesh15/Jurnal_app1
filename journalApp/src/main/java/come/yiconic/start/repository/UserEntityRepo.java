package come.yiconic.start.repository;

import come.yiconic.start.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntityRepo extends  MongoRepository<UserEntity, ObjectId> {
  UserEntity findByUserName(String username);
}
