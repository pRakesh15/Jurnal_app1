package come.yiconic.start.service;

import come.yiconic.start.entity.JurnalEntity;
import come.yiconic.start.entity.UserEntity;
import come.yiconic.start.repository.JurnalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//controller will call service ===controller---->service----->repository
//in service  we write all the business logic ...

@Service  //by this we store this inside java bean
public class JurnalService {

    //here i have to inject the repo interface to use the functionality

    @Autowired
    private JurnalEntryRepo jurnalEntryRepo;

    @Autowired
    private UserService userService;




    ///create a service for insert  data in database
//    @Transactional
    //this annotation treat the howl method as a single execution
    public void  saveEntry(JurnalEntity jurnalEntity,String useName) {
        try {
            //find user by user Id

            UserEntity user=userService.findByUserName(useName);
            if (user != null) {
                jurnalEntity.setDate(LocalDateTime.now());
                JurnalEntity savedJurnal = jurnalEntryRepo.save(jurnalEntity);
                //here we cant use setJurnalEntities() bcz the type is array..
                //ok just thing a senior if the code is break at this point
                //then what to do means the jurnal is created but not reffer in user
                // data consistance is left the chat so to prevent the problem
                //we use @Tranjecional..
                user.getJurnalEntities().add(savedJurnal);
                userService.saveUser(user);
                System.out.println(user);
            }
        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("An error occure",e);
        }
    }

    //create a service for get all the jurnnal
    public List<JurnalEntity> findAllEntry(){
      return  jurnalEntryRepo.findAll();
    }

    //create a service for get user by id
    public Optional<JurnalEntity> findById(ObjectId id){
         return jurnalEntryRepo.findById(id);
    }

    //create a service for find by id and delete the object
    public void findByIdAndDelete(ObjectId id, String userName){
        UserEntity user=userService.findByUserName(userName);
        user.getJurnalEntities().removeIf(x -> x.getId().equals(id)); //this is reffer that if the jurnal id is there then only remove it from the list
        userService.createUser(user);
         jurnalEntryRepo.deleteById(id);
    }

    //make a service for findbyid and update




}
