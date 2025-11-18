package com.curahub.curahub.availability;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;

import java.sql.Time;
//import java.time.LocalDateTime;
// import java.util.Date;

// import org.hibernate.annotations.Collate;

import com.curahub.curahub.doctor.Doctor;;

@Entity
@Table(name = "availability")
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "doctor")
    private Doctor doctorID;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;

    @Column(nullable = false, name = "length")
    private String length;

    @Column(nullable = false, name = "day")
    private String day = "Sun-Sat";

    public Availability() {
    }

    public Availability(long id, Doctor doctor, String start, String end, String length, String day) {
        this.id = id;
        this.doctorID = doctor;
        this.startTime = start;
        this.endTime = end;
        this.length = length;
        this.day = day;
    }

    public Availability(Doctor doctor, String start, String end, String length, String day) {
        this.doctorID = doctor;
        this.startTime = start;
        this.endTime = end;
        this.length = length;
        this.day = day;
    }

    public long getID() {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getLength() {
        return length;
    }

    public Doctor getDoctor() {
        return doctorID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setID(long id) {
        this.id = id;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setDoctor(Doctor doctor) {
        this.doctorID = doctor;
    }

}
