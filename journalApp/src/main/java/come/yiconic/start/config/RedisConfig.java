package come.yiconic.start.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    //will create this bean for connect the appliction to the redis in real time like what  we store in side the redis throug application
    //that will show in the local redis and what will store by using redis that will retrive by the applicaation....
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
         RedisTemplate redisTemplate=new RedisTemplate<>();
         redisTemplate.setConnectionFactory(factory);
         redisTemplate.setKeySerializer(new StringRedisSerializer());
         redisTemplate.setValueSerializer(new StringRedisSerializer());

         return  redisTemplate;
    }
}
