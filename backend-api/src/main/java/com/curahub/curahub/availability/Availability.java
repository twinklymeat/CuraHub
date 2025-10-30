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

import com.curahub.curahub.doctor.Doctor;;

@Entity
@Table(name="availability")
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="doctor")
    private Doctor doctorID;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;

    @Column(nullable = false, name="length")
    private Time length;

    public Availability(){
    }

    public Availability(long id, Doctor doctor, String start, String end, Time length){
        this.id = id;
        this.doctorID = doctor;
        this.startTime = start;
        this.endTime = end;
        this.length = length;
    }

    public Availability(Doctor doctor, String start, String end, Time length){
        this.doctorID = doctor;
        this.startTime = start;
        this.endTime = end;
        this.length = length;
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

    public Time getLength() {
        return length;
    }
    
    public Doctor getDoctor() {
        return doctorID;
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

    public void setLength(Time length) {
        this.length = length;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctorID = doctor;
    }

}
