package come.yiconic.start.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private ObjectId id;
    @NonNull
    private String userName;
    @NonNull
    private String password;

    private String email;

    private  boolean sentimentAnalysis;

    //here we have to make a reference between user and jurnalEntity
    //which is One-to-Many---->
    //make a new arrayList if there is no jurnal are available then it will show  a empty array instate of null...
    @DBRef
    private List<JurnalEntity> jurnalEntities=new ArrayList<>();

    private List<String> roles;
}
