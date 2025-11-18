package com.curahub.curahub.appointments;

import jakarta.persistence.Column;
// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

import com.curahub.curahub.doctor.Doctor;
import com.curahub.curahub.user.User;

@Entity
@Table(name = "appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column()
    private String notes = "unspecified";

    public Appointments() {
    }

    public Appointments(long id, LocalDateTime time, Doctor doctor, User user, String notes) {
        this.id = id;
        this.time = time;
        this.doctor = doctor;
        this.user = user;
        this.notes = notes;
    }

    public Appointments(LocalDateTime time, Doctor doctor, User user, String notes) {
        this.time = time;
        this.doctor = doctor;
        this.user = user;
        this.notes = notes;
    }

    public long getID() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public User getUser() {
        return user;
    }

    public void setID(long id) {
        this.id = id;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
