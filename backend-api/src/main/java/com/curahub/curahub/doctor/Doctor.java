package com.curahub.curahub.doctor;

// import jakarta.persistence.*;

import jakarta.persistence.Column;
// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

// import java.util.List;

// import com.curahub.curahub.availability.Availability;
import com.curahub.curahub.user.User;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne
    @JoinColumn(name="doctor_user", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private String description;

    public Doctor() {
        
    }
    
    public Doctor(long id, User doctor, String description) {
        this.id = id;
        this.user = doctor;
        this.description = description;
    }

    public Doctor(User doctor, String description) {
        this.user = doctor;
        this.description = description;
    }

    public long getID() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

}
