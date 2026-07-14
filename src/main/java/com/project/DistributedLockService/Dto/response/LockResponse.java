package com.project.DistributedLockService.Dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LockResponse {
    private boolean success;
    private String message;
    private String resourceName;
    private String ownerId;
}
