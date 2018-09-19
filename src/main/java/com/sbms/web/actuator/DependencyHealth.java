package com.sbms.web.actuator;

import java.util.Arrays;
import java.util.List;

public class DependencyHealth {
    private final String greetingServiceStatus;

    public DependencyHealth(String greetingServiceStatus) {
        this.greetingServiceStatus = greetingServiceStatus;
    }

    public String getGreetingServiceStatus() {
        return greetingServiceStatus;
    }

    public String getOverallStatus() {
        List<String> allStatuses = allStatuses();
        if (allStatuses.stream().anyMatch("DOWN"::equals)) {
            return "DOWN";
        }
        if (allStatuses.stream().anyMatch("OUT_OF_SERVICE"::equals)) {
            return "OUT_OF_SERVICE";
        }
        if (allStatuses.stream().anyMatch("UNKNOWN"::equals)) {
            return "UNKNOWN";
        }
        return "UP";
    }

    private List<String> allStatuses() {
        return Arrays.asList(greetingServiceStatus);
    }
}
