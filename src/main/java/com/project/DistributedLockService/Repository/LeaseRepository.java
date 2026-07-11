package com.project.DistributedLockService.Repository;
import com.project.DistributedLockService.Entity.Lock;
import com.project.DistributedLockService.Entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeaseRepository extends JpaRepository<Lease, UUID> {

    Optional<Lease> findByLock(Lock lock);

    List<Lease> findByExpiryTimeBefore(LocalDateTime currentTime);

}