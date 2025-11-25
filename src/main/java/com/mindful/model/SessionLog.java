package com.mindful.model;

import java.util.Date;

public class SessionLog {
    private int id;
    private int userId;
    private int meditationId;
    private int durationSeconds;
    private Date startedAt;
    private Date endedAt;
    private int rating;
    private String notes;

    // getters/setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getMeditationId() { return meditationId; }
    public void setMeditationId(int meditationId) { this.meditationId = meditationId; }
    public int getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(int durationSeconds) { this.durationSeconds = durationSeconds; }
    public Date getStartedAt() { return startedAt; }
    public void setStartedAt(Date startedAt) { this.startedAt = startedAt; }
    public Date getEndedAt() { return endedAt; }
    public void setEndedAt(Date endedAt) { this.endedAt = endedAt; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
