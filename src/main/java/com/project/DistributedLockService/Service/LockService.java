package com.project.DistributedLockService.Service;

import com.project.DistributedLockService.Dto.request.AcquireLockRequest;
import com.project.DistributedLockService.Dto.response.LockResponse;
import com.project.DistributedLockService.Entity.Lease;
import com.project.DistributedLockService.Entity.Lock;
import com.project.DistributedLockService.Enums.LockStatus;
import com.project.DistributedLockService.Repository.LeaseRepository;
import com.project.DistributedLockService.Repository.LockRepository;
import com.project.DistributedLockService.Repository.RedisLockRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LockService {

    private final RedisLockRepository redisLockRepository;
    private final LockRepository lockRepository;
    private final LeaseRepository leaseRepository;

    public LockService(RedisLockRepository redisLockRepository,
                       LockRepository lockRepository,
                       LeaseRepository leaseRepository) {

        this.redisLockRepository = redisLockRepository;
        this.lockRepository = lockRepository;
        this.leaseRepository = leaseRepository;
    }
    public LockResponse acquireLock(AcquireLockRequest request) {

        boolean acquired = redisLockRepository.acquireLock(
                request.getResourceName(),
                request.getOwnerId(),
                request.getLeaseDuration()
        );

        if (!acquired) {
            return LockResponse.builder()
                    .success(false)
                    .message("Resource is already locked")
                    .build();
        }

        Lock lock = Lock.builder()
                .resourceName(request.getResourceName())
                .ownerId(request.getOwnerId())
                .status(LockStatus.ACTIVE)
                .build();

        lockRepository.save(lock);

        Lease lease = Lease.builder()
                .lock(lock)
                .leaseDuration(request.getLeaseDuration())
                .lastHeartbeat(LocalDateTime.now())
                .expiryTime(
                        LocalDateTime.now()
                                .plusSeconds(request.getLeaseDuration())
                )
                .build();

        leaseRepository.save(lease);

        return LockResponse.builder()
                .success(true)
                .message("Lock acquired successfully")
                .resourceName(lock.getResourceName())
                .ownerId(lock.getOwnerId())
                .build();
    }


}
