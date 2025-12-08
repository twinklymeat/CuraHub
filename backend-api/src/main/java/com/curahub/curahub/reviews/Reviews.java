package com.curahub.curahub.reviews;

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

import com.curahub.curahub.user.User;
import com.curahub.curahub.appointments.Appointments;
import com.curahub.curahub.doctor.Doctor;;;


@Entity
@Table(name = "reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // @Column(nullable = true)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = true)
    private long rating;

    @Column(nullable = false)
    private String content;

    // @ManyToOne
    // @JoinColumn(name="appt_id")
    // private Appointments appointment;

    @Column(nullable = true)
    private String response;
    // set to true if author is user, false if author is the doctor

    

    public Reviews() {

    }

    public Reviews(long id, Doctor doctor, User user, long rating, String content, String response) {
        this.id = id;
        this.doctor = doctor;
        this.user = user;
        this.rating = rating;
        this.content = content;
        this.response = response;
        // this.appointment = appointment;
    }
    public Reviews(long id, Doctor doctor, User user, long rating, String content) {
        this.id = id;
        this.doctor = doctor;
        this.user = user;
        this.rating = rating;
        this.content = content;
        this.response = null;
        // this.appointment = appointment;
    }

    public Reviews(Doctor doctor, User user, long rating, String content,  String response) {
        this.doctor = doctor;
        this.user = user;
        this.rating = rating;
        this.content = content;
        this.response = response;
        // this.appointment = appointment;
    }

    public Reviews(Doctor doctor, User user, long rating, String content) {
        this.doctor = doctor;
        this.user = user;
        this.rating = rating;
        this.content = content;
        this.response = null;
        // this.appointment = appointment;
    }

    public long getID() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public User getUser() {
        return user;
    }

    public long getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    // public Appointments getAppointment() {
    //     return appointment;
    // }

    public String getResponse() {
        return response;
    }

    public void setID(long id) {
        this.id = id;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // public void setAppointment(Appointments appointment) {
    //     this.appointment = appointment;
    // }

    public void setResponse(String response) {
        this.response = response;
    }


}
