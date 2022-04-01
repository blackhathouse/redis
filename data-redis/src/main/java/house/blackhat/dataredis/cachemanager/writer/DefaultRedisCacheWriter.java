package house.blackhat.dataredis.cachemanager.writer;

import org.springframework.data.redis.cache.CacheStatistics;
import org.springframework.data.redis.cache.CacheStatisticsCollector;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.time.Duration;

public class DefaultRedisCacheWriter implements RedisCacheWriter {

    private final RedisCacheWriter redisCacheWriter;

    public DefaultRedisCacheWriter(RedisConnectionFactory connectionFactory) {
        Assert.notNull(connectionFactory, "ConnectionFactory must not be null!");
        redisCacheWriter = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
    }

    @Override
    public void put(
            @NonNull String name, @NonNull byte[] key, @NonNull byte[] value, Duration duration) {
        redisCacheWriter.put(name, key, value, duration);
    }

    @Override
    public byte[] get(@NonNull String name, @NonNull byte[] key) {
        return redisCacheWriter.get(name, key);
    }

    @Override
    public byte[] putIfAbsent(
            @NonNull String name, @NonNull byte[] key, @NonNull byte[] value, Duration duration) {
        return redisCacheWriter.putIfAbsent(name, key, value, duration);
    }

    @Override
    public void remove(@NonNull String name, @NonNull byte[] value) {
        redisCacheWriter.remove(name, value);
    }

    @Override
    public void clean(@NonNull String name, @NonNull byte[] value) {
        redisCacheWriter.clean(name, value);
    }

    @Override
    public void clearStatistics(@NonNull String name) {
        redisCacheWriter.clearStatistics(name);
    }

    @NonNull
    @Override
    public RedisCacheWriter withStatisticsCollector(
            @NonNull CacheStatisticsCollector cacheStatisticsCollector) {
        return redisCacheWriter.withStatisticsCollector(cacheStatisticsCollector);
    }

    @NonNull
    @Override
    public CacheStatistics getCacheStatistics(@NonNull String name) {
        return redisCacheWriter.getCacheStatistics(name);
    }

}
