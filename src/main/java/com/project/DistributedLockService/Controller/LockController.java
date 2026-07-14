package com.project.DistributedLockService.Controller;

import com.project.DistributedLockService.Dto.request.AcquireLockRequest;
import com.project.DistributedLockService.Dto.request.HeartbeatRequest;
import com.project.DistributedLockService.Dto.response.LockResponse;
import com.project.DistributedLockService.Service.LockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locks")
public class LockController{
    private final LockService lockService;

    public LockController(LockService lockService) {
        this.lockService = lockService;
    }

    @PostMapping
    public ResponseEntity<LockResponse> acquireLock(@RequestBody AcquireLockRequest request) {
        LockResponse response = lockService.acquireLock(request);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{resourceName}")
    public ResponseEntity<String> releaseLock(
            @PathVariable String resourceName) {

        return ResponseEntity.ok(
                "Release Lock API Working for " + resourceName
        );
    }


    @PostMapping("/heartbeat")
    public ResponseEntity<String> heartbeat() {

        return ResponseEntity.ok("Heartbeat API Working");
    }

    @GetMapping("/{resourceName}")
    public ResponseEntity<String> getLockStatus(
            @PathVariable String resourceName) {

        return ResponseEntity.ok(
                "Get Lock Status API Working for " + resourceName
        );
    }

}

