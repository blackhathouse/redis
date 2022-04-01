package house.blackhat.dataredis.cachemanager;

import house.blackhat.dataredis.cachemanager.writer.DefaultRedisCacheWriter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class DefaultCacheManager extends CachingConfigurerSupport {

    private final RedisConnectionFactory connectionFactory;
    private final RedisCacheConfiguration dataRedisConfiguration;

    public DefaultCacheManager(RedisConnectionFactory connectionFactory, RedisCacheConfiguration dataRedisConfiguration) {
        this.connectionFactory = connectionFactory;
        this.dataRedisConfiguration = dataRedisConfiguration;
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return RedisCacheManager.builder()
                .cacheWriter(new DefaultRedisCacheWriter(connectionFactory))
                .cacheDefaults(dataRedisConfiguration)
                .build();
    }

}
