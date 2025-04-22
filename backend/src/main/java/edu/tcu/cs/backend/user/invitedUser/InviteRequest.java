package edu.tcu.cs.backend.user.invitedUser;

import java.util.List;

public class InviteRequest {
    private List<String> emails;

    // Getters and setters
    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
