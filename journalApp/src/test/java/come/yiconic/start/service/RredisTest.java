package come.yiconic.start.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RredisTest {


    @Autowired
    private RedisTemplate redisTemplate;
@Disabled
    @Test
    void  testRedis(){
redisTemplate.opsForValue().set("salary","15k");
        Object salary = redisTemplate.opsForValue().get("salary");
        int a=1;
    }
}
