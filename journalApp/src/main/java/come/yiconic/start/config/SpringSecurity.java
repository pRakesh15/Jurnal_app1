package come.yiconic.start.config;

import come.yiconic.start.service.UserDetailsServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceimpl userDetailsServiceimpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //as we know when i add spring security all the routs are swcure
        // by making manual function we specify  the  routs..
        http.authorizeRequests()
                //make the routs authenticated in , separated value.
                .antMatchers("/jurnalist/**","/user/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                //expect the above routs all the routs are free to access
                .anyRequest().permitAll()
                //direct return to http .
                .and()
                //specify the basic auth ...
                .httpBasic();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //in this authentication  when user pass the user name and password the given password
        // is hashed and the hashed password is matched if it matched then it will authenticated and if not match and then it will not authenticated
        auth.userDetailsService(userDetailsServiceimpl).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        //take the password and make it hassing
        return  new BCryptPasswordEncoder();
    }
}
