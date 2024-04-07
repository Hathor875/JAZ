package org.example.jaz1.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReportResponse {
    private UUID id;
    private LocalDateTime date;
    private String title;
    private String description;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
