package house.blackhat.dataredis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.lang.NonNull;

import java.time.Duration;
import java.util.Objects;

@Configuration
@EnableConfigurationProperties(value = {CacheProperties.class, RedisProperties.class})
@ConfigurationPropertiesScan({
        "org.springframework.boot.autoconfigure.cache",
        "org.springframework.boot.autoconfigure.data.redis"
})
public class DataRedisConfiguration {

    private final CacheProperties cacheProperties;

    @Value("${spring.redis.host:#{null}}")
    String redisHostName;

    @Value("${spring.redis.port:0}")
    Integer redisHostPort;

    @Value("${spring.redis.password:#{null}}")
    String redisPassword;

    public DataRedisConfiguration(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    @Bean
    public RedisCacheConfiguration getRedisCacheConfiguration() {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

        if (!cacheProperties.getRedis().isCacheNullValues()) {
            redisCacheConfiguration = redisCacheConfiguration.disableCachingNullValues();
        }

        Duration duration = cacheProperties.getRedis().getTimeToLive();
        if (Objects.nonNull(duration)) {
            redisCacheConfiguration = redisCacheConfiguration.entryTtl(duration);
        }

        redisCacheConfiguration = redisCacheConfiguration.computePrefixWith(cacheKeyPrefix());
        return redisCacheConfiguration;
    }

    protected CacheKeyPrefix cacheKeyPrefix() {
        return new CacheKeyPrefix() {
            @NonNull
            @Override
            public String compute(@NonNull String cacheName) {
                return "blackhat" + cacheName + CacheKeyPrefix.SEPARATOR;
            }
        };
    }

}
