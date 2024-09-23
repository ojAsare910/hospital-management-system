package com.ojasare.hospitalmgmt.config;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom")
public class CustomEndpoint {

    private String status = "ONLINE";

    @ReadOperation
    public CustomInfo getInfo() {
        return new CustomInfo(status);
    }

    @WriteOperation
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    @DeleteOperation
    public void deleteStatus() {
        this.status = null;
    }

    public static class CustomInfo {
        private String status;

        public CustomInfo(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }
}
