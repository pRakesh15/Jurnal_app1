package come.yiconic.start;

//the above package is the base package of the project.


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//here the annotation  contains combination of 3 annotation
//1->@Configuration--configure all the needs like bean ioc etc....
//2->@EnableAutoConfiguration
//3->ComponentScan  -- refers to scan all the classes under the "come.yiconic.start" package
// and add the bean to the ioc container ...

//this annotation is only apply once in the project  in the entry point .
// that means the main method is the entry point of the spring project.
@SpringBootApplication
//here i have to add annotation for make the scan of transition management .
//@EnableTransactionManagement
public class JournalApplication {
    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);}

    //out side the main method have to crate a method to store the transition inside the bean

//    @Bean
//    //implement the interface for handel the transaction..
//    public PlatformTransactionManager manager(MongoDatabaseFactory dbFactory){
//      return  new MongoTransactionManager(dbFactory);
//    }







}