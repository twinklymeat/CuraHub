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

    @Column(nullable = false)
    private long rating;

    @Column(nullable = false)
    private String content;

}
