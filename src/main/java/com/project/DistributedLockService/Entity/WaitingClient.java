package com.project.DistributedLockService.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "waiting_clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaitingClient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "resource_name", nullable = false)
    private String resourceName;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "request_time", nullable = false, updatable = false)
    private LocalDateTime requestTime;

    @PrePersist
    public void onCreate() {
        requestTime = LocalDateTime.now();
    }
}
