package come.yiconic.start.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


//the below annotation make the mapping of the class to the database what a orm actually do.

@Document(collection = "journal_entries") //here we can specify the collection name...
//if we  have more 10 or 20 fields then there is  the mejor problem that is
//we have to create the getter and setter for all the 20 field and what i think ,
//thar is  a littel bit of hectic and for resolving thise problem
// we have Lombook projectLombook --->
//it provide us the boilerplte code that compile at the time of  code run
//we just  add the annotation according to this the loombook will compile the code .
// all the things acchived by lombook autometically during compile time based on annotation's...
//for now we can use Getter and setter..
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Builder
//instate of all the above annotation we can simply use
@Data
//By this annotation we can acchive all the loombok aannotation's..
@NoArgsConstructor // for deserialize the constructor
public class JurnalEntity {


    //here  i have to make a field primary key......

    //in this i make the id as primary.
    @Id
    private ObjectId id;
    private String title;

    private String content;

    private LocalDateTime date;





}
