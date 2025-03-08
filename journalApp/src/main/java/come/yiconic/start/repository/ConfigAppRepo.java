package come.yiconic.start.repository;

import come.yiconic.start.entity.ConfigJurnalAppEntity;
import come.yiconic.start.entity.JurnalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ConfigAppRepo  extends MongoRepository<ConfigJurnalAppEntity, ObjectId> {
}
