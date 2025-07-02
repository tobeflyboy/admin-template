package com.nutcracker.config;

import com.nutcracker.common.constant.CacheableKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Redis 缓存配置
 *
 * @author 胡桃夹子
 * @since 2023/12/4
 */
@Slf4j
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
@Configuration
@ConditionalOnProperty(name = "spring.cache.enabled") // xxl.job.enabled = true 才会自动装配
public class RedisCacheConfig {

    /**
     * 自定义 RedisCacheManager
     * <p>
     * 修改 Redis 序列化方式，默认 JdkSerializationRedisSerializer
     *
     * @param redisConnectionFactory {@link RedisConnectionFactory}
     * @param cacheProperties        {@link CacheProperties}
     * @return {@link RedisCacheManager}
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory, CacheProperties cacheProperties) {
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                // 默认策略，未配置的 key 会使用这个
                .cacheDefaults(redisCacheConfiguration(cacheProperties))
                // 指定 key 策略
                .withInitialCacheConfigurations(getCacheConfigurations())
                .build();
    }

    private Map<String, RedisCacheConfiguration> getCacheConfigurations() {
        Map<String, RedisCacheConfiguration> getCacheConfigurations = new HashMap<>();
        CacheableKey.getCacheableKeyMap().forEach((key, value) -> {
            log.debug("CacheManager cacheNames=[{}],timeout=[{}]s", key, value);
            getCacheConfigurations.put(key, this.getRedisCacheConfigurationWithTtl(value));
        });
        return getCacheConfigurations;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(long seconds) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()))
                .entryTtl(Duration.ofSeconds(seconds));
        return config;
    }

    /**
     * 自定义 RedisCacheConfiguration
     *
     * @param cacheProperties {@link CacheProperties}
     * @return {@link RedisCacheConfiguration}
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()));
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));

        CacheProperties.Redis redisProperties = cacheProperties.getRedis();

        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        // 覆盖默认key双冒号  CacheKeyPrefix#prefixed
        config = config.computePrefixWith(name -> name + ":");
        return config;
    }

}
