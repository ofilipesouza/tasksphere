package dev.ofilipesouza.tasksphere.infra.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

@Configuration
@EnableRedisHttpSession(redisNamespace = "tasksphere")
public class SessionConfig {

    @Bean
    public RedisHttpSessionConfiguration redisHttpSessionConfiguration(){
        RedisHttpSessionConfiguration configuration = new RedisHttpSessionConfiguration();
        return configuration;
    }
    
}
