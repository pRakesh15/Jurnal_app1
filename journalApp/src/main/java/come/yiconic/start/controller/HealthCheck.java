package come.yiconic.start.controller;


import come.yiconic.start.entity.UserEntity;
import come.yiconic.start.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String healthChecker(){
        return "Ok";
    }

    //by this we can get the value  of anything we store in application property...
//    @Value("${my_Name}")
//    private String myName;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserEntity user){
        try {

            userService.createUser(user);
            return new ResponseEntity<UserEntity>(user, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
