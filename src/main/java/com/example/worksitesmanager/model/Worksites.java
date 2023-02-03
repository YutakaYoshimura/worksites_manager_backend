package com.example.worksitesmanager.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "worksites")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Worksites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sub_name", nullable = false)
    private String sub_name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "staff_name", nullable = false)
    private String staff_name;

    // @Basic(fetch = FetchType.LAZY)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo", nullable = false)
    @Lob
    @Type(type="org.hibernate.type.ImageType")
    private byte[] photo;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "start_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date start_at;

    @Column(name = "end_at", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date end_at;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at;
}
