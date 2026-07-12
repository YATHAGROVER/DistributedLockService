package com.project.DistributedLockService.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class RedisLockRepository {

    private final StringRedisTemplate redisTemplate;

    /**
     * Creates a Redis key for a resource.
     * Example: payment:123 -> lock:payment:123
     */
    private String getLockKey(String resourceName) {
        return "lock:" + resourceName;
    }

    /**
     * Acquire a lock if it is not already held.
     */
    public boolean acquireLock(String resourceName,
                               String ownerId,
                               long leaseDurationSeconds) {

        Boolean acquired = redisTemplate.opsForValue()
                .setIfAbsent(
                        getLockKey(resourceName),
                        ownerId,
                        Duration.ofSeconds(leaseDurationSeconds)
                );

        return Boolean.TRUE.equals(acquired);
    }

    /**
     * Release a lock.
     */
    public boolean releaseLock(String resourceName) {

        Boolean deleted = redisTemplate.delete(getLockKey(resourceName));

        return Boolean.TRUE.equals(deleted);
    }

    /**
     * Extend the lease of an existing lock.
     */
    public boolean renewLease(String resourceName,
                              long leaseDurationSeconds) {

        Boolean renewed = redisTemplate.expire(
                getLockKey(resourceName),
                Duration.ofSeconds(leaseDurationSeconds)
        );

        return Boolean.TRUE.equals(renewed);
    }

    /**
     * Check whether a resource is locked.
     */
    public boolean isLocked(String resourceName) {

        return Boolean.TRUE.equals(
                redisTemplate.hasKey(getLockKey(resourceName))
        );
    }

    /**
     * Returns the owner of the lock.
     */
    public String getLockOwner(String resourceName) {

        return redisTemplate.opsForValue()
                .get(getLockKey(resourceName));
    }

}
