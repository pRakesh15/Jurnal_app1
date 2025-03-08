package come.yiconic.start.controller;


import come.yiconic.start.entity.JurnalEntity;
import come.yiconic.start.entity.UserEntity;
import come.yiconic.start.service.JurnalService;
import come.yiconic.start.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
//the below anotation is for make a root endpoint like
//what ever the functions or endpoints inside this class that all o through this endpoint
@RequestMapping("/jurnalist")
public class Jurnallontroller {
//create a get rout defaut is "/" until we define it by maually
    //here i have to inject the service class to use the functionality.
    @Autowired
    private JurnalService jurnalService;


    @Autowired
    private UserService userService;

//lets text this end point...............
    @GetMapping()
    //have to make the response in response entity  for that we can send  the status code and message also..
    public ResponseEntity<?> getAllJurnal(){
        List<JurnalEntity> allJurnalEntity= jurnalService.findAllEntry();
        if(allJurnalEntity!=null && !allJurnalEntity.isEmpty()){
            return new ResponseEntity<>(allJurnalEntity, HttpStatus.OK);
        }
        //there is a  cons if we want to add some custom message then we have to add it in to a map
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//
    @GetMapping("getMy")
    //have to make the response in response entity  for that we can send  the status code and message also..
    public ResponseEntity<?> getAllJurnalOfUser(){
//by this we can find the user name or any user field who is authenticate.....
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String useName=authentication.getName();
        UserEntity user=userService.findByUserName(useName);

        if(user!=null)
        {
            List<JurnalEntity> allJurnalEntity= user.getJurnalEntities();

            if(allJurnalEntity!=null && !allJurnalEntity.isEmpty()){
                return new ResponseEntity<>(allJurnalEntity, HttpStatus.OK);
            }
        }

        //there is a  cons if we want to add some custom message then we have to add it in to a map
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("createJurnal")
    //here  RequestBody work as take the data from the request and turn it into a java object to use in my code
    //in post mapping i have to add the user also to the jurnal.
    public ResponseEntity<JurnalEntity> ceateJurnal(@RequestBody JurnalEntity entry){
        //call the jurnal service by help of service ...
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String useName=authentication.getName();
            jurnalService.saveEntry(entry,useName);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //creating a get jurnal by id by using pathParameter
    @GetMapping("/{id}")
    //when we pass somthing in frontend by path params we can access bt this annotation.
    public ResponseEntity<?> getById(@PathVariable ObjectId id){
        //here i use orElse for make the return type optional like if there is no data found
        Optional<JurnalEntity> jurnalEntity=jurnalService.findById(id);
        if(jurnalEntity.isPresent()){
            return new ResponseEntity<>(jurnalEntity,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //delete by id and also delete from user also
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        Optional<JurnalEntity> jurnalEntity=jurnalService.findById(id);
        if(jurnalEntity.isPresent()){
            jurnalService.findByIdAndDelete(id,userName);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //update it dont need any extra type of business logic ..
    //so will direct do the things inside controller.


//    @PutMapping("/{id}")
//    public  ResponseEntity<JurnalEntity> updateJurnal(@PathVariable ObjectId id,@RequestBody JurnalEntity jurnalEntity){
//        JurnalEntity oldData=jurnalService.findById(id).orElse(null);
//        //after make response entity we will go forword...
//
////        if(oldData !=null){
////            oldData.setTitle(jurnalEntity.getTitle()!=null && !jurnalEntity.getTitle().equals("")? jurnalEntity.getTitle() : oldData.getTitle());
////            oldData.setContent(jurnalEntity.getContent()!=null && !jurnalEntity.getContent().equals("")? jurnalEntity.getContent() : oldData.getContent());
////            jurnalService.saveEntry(oldData, user);
////            return new ResponseEntity<>(oldData,HttpStatus.OK);
////        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}

