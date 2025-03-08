package come.yiconic.start.service;

import come.yiconic.start.entity.UserEntity;
import come.yiconic.start.repository.UserEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//it will go with the authentication....
@Component
public class UserDetailsServiceimpl implements UserDetailsService {

    @Autowired
    private UserEntityRepo userEntityRepo;
    @Override
    //by this function we can access the user details by the user name .
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity user= userEntityRepo.findByUserName(username);
//        System.out.println(user);
       if(user!=null){
           return org.springframework.security.core.userdetails.User.builder()
                   .username(user.getUserName())
                   .password(user.getPassword())
                   .roles(user.getRoles().toArray(new String[0]))
                   .build();
       }
       throw new UsernameNotFoundException("User not Found"+username);
    }
}
