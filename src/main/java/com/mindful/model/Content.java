package com.mindful.model;

public class Content {
    private int id;
    private String title;
    private String description;
    private String mediaUrl;
    private String mediaType;
    private int createdBy;

    public Content() {}

    public Content(int id, String title, String description, String mediaUrl, String mediaType, int createdBy) {
        this.id = id; this.title = title; this.description = description;
        this.mediaUrl = mediaUrl; this.mediaType = mediaType; this.createdBy = createdBy;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getMediaUrl() { return mediaUrl; }
    public void setMediaUrl(String mediaUrl) { this.mediaUrl = mediaUrl; }
    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }
    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }
}
