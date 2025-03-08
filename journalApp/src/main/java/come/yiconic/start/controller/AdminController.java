package come.yiconic.start.controller;

import come.yiconic.start.entity.UserEntity;
import come.yiconic.start.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    //getAllUser
    @GetMapping("/all-user")
    public ResponseEntity<?> getAllUser(){

        List<UserEntity> getallUser=userService.findAllUSer();
        if(getallUser!=null && !getallUser.isEmpty()){
            return new ResponseEntity<>(getallUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody UserEntity admin){
        try {
            userService.createAdmin(admin);
            return new ResponseEntity<UserEntity>(admin, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
