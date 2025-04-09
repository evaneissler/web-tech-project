package edu.tcu.cs.backend.user;

import edu.tcu.cs.backend.availability.Availability;
import edu.tcu.cs.backend.system.StringListConverter;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;

    @Convert(converter = StringListConverter.class)
    private List<String> positions = new ArrayList<>();

    private Boolean enabled = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Availability> availabilities;

    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {  return email; }

    public void setEmail(String email) {this.email = email; }

    public String getPhoneNumber(String phoneNumber) {  return phoneNumber;  }

    public void setPhoneNumber(String phoneNumber) {}

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public List<String> getPositions() {
        return positions;
    }

    public void addPosition(String position) {
        this.positions.add(position);
    }

    public void removePosition(String position) {
        this.positions.remove(position);
    }

    public Boolean getEnabled() { return enabled; }

    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

}
