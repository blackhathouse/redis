package house.blackhat.dataredis.connectionfactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class LettuceConnectionFactoryConfig {

    @Value("${spring.redis.host:#{null}}")
    String redisHostName;

    @Value("${spring.redis.port:0}")
    Integer redisHostPort;

    @Value("${spring.redis.password:#{null}}")
    String redisPassword;

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        if (redisHostName == null || redisHostPort == 0) {
            throw new RuntimeException(
                    "When using EnableDataRedis annotation, the following configurations are mandatory in your application.properties or config-server: 'spring.redis.host', 'spring.redis.port'");
        }
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisHostName);
        configuration.setPort(redisHostPort);
        if (redisPassword != null) configuration.setPassword(redisPassword);
        else configuration.setPassword(RedisPassword.none());
        return new LettuceConnectionFactory(configuration);
    }

}
