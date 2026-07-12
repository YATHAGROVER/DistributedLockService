package com.project.DistributedLockService.Repository;

import com.project.DistributedLockService.Entity.WaitingClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WaitingClientRepository extends JpaRepository<WaitingClient, UUID> {

    List<WaitingClient> findByResourceNameOrderByRequestTimeAsc(String resourceName);

    void deleteByClientId(String clientId);
}
