package com.project.DistributedLockService.Dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcquireLockRequest {
    private String resourceName;
    private String ownerId;
    private Integer leaseDuration;
}
