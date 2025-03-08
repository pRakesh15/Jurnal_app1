package come.yiconic.start.service;


import come.yiconic.start.entity.UserEntity;
import come.yiconic.start.repository.UserEntityRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    //here we inject  the dependency in field type
    // here field type dependency injection is occure.
    @Autowired
    private UserEntityRepo userEntityRepo;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    //create user with jwt and passwordBycript.. with SHA56 algo

    //for now just create a user..
    public void createUser(UserEntity userEntity){
        //here 1st save the password n has and then
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList("USER"));
        userEntityRepo.save(userEntity);
    }
    //method for create a admin
    public void createAdmin(UserEntity userEntity){
        //here 1st save the password n has and then
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList("ADMIN"));
        userEntityRepo.save(userEntity);
    }

    //another method for just save the user...
    public void saveUser(UserEntity userEntity){
        userEntityRepo.save(userEntity);
    }

    //findByUserName
    public UserEntity findByUserName(String  userNAme){
        return userEntityRepo.findByUserName(userNAme);
    }

    //getAllUser   lets test this with junit..
    public List<UserEntity> findAllUSer(){
        return userEntityRepo.findAll();
    }
    //getUserById
    public UserEntity findOneUser(ObjectId id){
        return userEntityRepo.findById(id).orElse(null);
    }
    //DeleteUser
    public void deleteUser(ObjectId id){
        userEntityRepo.deleteById(id);
    }

    //updateUser
}
