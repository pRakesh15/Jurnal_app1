package come.yiconic.start.controller;


import come.yiconic.start.entity.JurnalEntity;
import come.yiconic.start.entity.UserEntity;
import come.yiconic.start.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //UpdateUser
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user ,@PathVariable ObjectId userId ){
        System.out.println(userId);
        return  new ResponseEntity<>(HttpStatus.OK);

    }
    //DeleteUser
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id){

            userService.deleteUser(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }
    //LogoutUser
}
