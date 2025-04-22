package edu.tcu.cs.backend.user.invitedUser;

import edu.tcu.cs.backend.availability.Availability;
import edu.tcu.cs.backend.system.StringListConverter;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class InvitedUser  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}