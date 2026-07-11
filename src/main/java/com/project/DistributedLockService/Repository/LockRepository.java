package com.project.DistributedLockService.Repository;

import com.project.DistributedLockService.Entity.Lock;
import com.project.DistributedLockService.Enums.LockStatus;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface LockRepository extends JpaRepository<Lock, UUID> {
    Optional<Lock> findByResourceName(String resourceName);

    Optional<Lock> findByResourceNameAndStatus(String resourceName, LockStatus status);

    boolean existsByResourceName(String resourceName);

    void deleteByResourceName(String resourceName);

}
