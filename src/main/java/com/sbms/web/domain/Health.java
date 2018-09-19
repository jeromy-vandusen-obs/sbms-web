package com.sbms.web.domain;

public class Health {
    private String status;

    public Health() {
        super();
    }

    public Health(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
