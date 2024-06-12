package org.example.jaz1.Service;

import java.util.UUID;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(UUID id) {
        super("Could not find report with ID: " + id);
    }
}
