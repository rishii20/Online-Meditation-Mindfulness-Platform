package com.mindful.model;

import java.util.Date;

public class Message {
    private int id;
    private int fromUser;
    private int toUser;
    private String content;
    private Date sentAt;
    private boolean isRead;

    // getters/setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getFromUser() { return fromUser; }
    public void setFromUser(int fromUser) { this.fromUser = fromUser; }
    public int getToUser() { return toUser; }
    public void setToUser(int toUser) { this.toUser = toUser; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Date getSentAt() { return sentAt; }
    public void setSentAt(Date sentAt) { this.sentAt = sentAt; }
    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }
}
