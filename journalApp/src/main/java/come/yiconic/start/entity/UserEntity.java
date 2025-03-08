package come.yiconic.start.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data

public class UserEntity {
    @Id
    private ObjectId id;
    @NonNull
    private String userName;
    @NonNull
    private String password;

    //here we have to make a reference between user and jurnalEntity
    //which is One-to-Many---->
    //make a new arrayList if there is no jurnal are available then it will show  a empty array instate of null...
    @DBRef
    private List<JurnalEntity> jurnalEntities=new ArrayList<>();

    private List<String> roles;
}
