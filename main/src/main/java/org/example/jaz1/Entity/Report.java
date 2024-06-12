package org.example.jaz1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
public class Report {
    public UUID getId() {
        return id;
    }

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

    public LocalDateTime getDate() {
        return date;
    }



    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime date;
    private String title;
    private String description;


}
