package com.project.DistributedLockService.Dto.request;

public class AcquireLockRequest {
    private String resourceName;
    private String ownerId;
    private Integer leaseDuration;
}
