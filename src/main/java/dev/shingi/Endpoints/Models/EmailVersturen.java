package dev.shingi.endpoints.models;

public class EmailVersturen {
    private boolean shouldSend;
    private String email;
    private String ccEmail;

    public boolean isShouldSend() {
        return shouldSend;
    }
    public void setShouldSend(boolean shouldSend) {
        this.shouldSend = shouldSend;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCcEmail() {
        return ccEmail;
    }
    public void setCcEmail(String ccEmail) {
        this.ccEmail = ccEmail;
    }
}

