package com.project.DistributedLockService.Controller;

import com.project.DistributedLockService.Dto.request.AcquireLockRequest;
import com.project.DistributedLockService.Dto.request.HeartbeatRequest;
import com.project.DistributedLockService.Dto.response.LockResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locks")
public class LockController{

    @PostMapping
    public ResponseEntity<String> acquireLock() {

        return ResponseEntity.ok("Acquire Lock API Working");
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

