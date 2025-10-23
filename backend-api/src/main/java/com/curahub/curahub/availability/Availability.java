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
import java.time.LocalDateTime;
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
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private Time length;

    public Availability(){
    }

    public Availability(long id, Doctor doctor, LocalDateTime start, LocalDateTime end, Time length){
        this.id = id;
        this.doctor = doctor;
        this.startTime = start;
        this.endTime = end;
        this.length = length;
    }

    public Availability(Doctor doctor, LocalDateTime start, LocalDateTime end, Time length){
        this.doctor = doctor;
        this.startTime = start;
        this.endTime = end;
        this.length = length;
    }

}
