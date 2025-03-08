package come.yiconic.start.service;

import come.yiconic.start.entity.UserEntity;
import come.yiconic.start.repository.UserEntityRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {


    @Autowired
    private UserEntityRepo userEntityRepo;

    @Disabled
    @Test
    public void tsetAdd(){
        assertEquals(4,3+1);
        assertNotNull(userEntityRepo.findAll());
        UserEntity user=userEntityRepo.findByUserName("ram");
        assertFalse(user.getJurnalEntities().isEmpty());
    }
    @Disabled
    @ParameterizedTest
@CsvSource({
        "1,1,2",
        "2,2,4",
        "5,6,11"
})
    public void test(int a,int b,int expected){
       assertEquals(expected ,a+b);
    }

}
