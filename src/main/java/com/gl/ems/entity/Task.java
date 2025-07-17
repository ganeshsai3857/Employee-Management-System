package com.gl.ems.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="task")
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title",nullable = false,unique = true)
    private String title;

    @Column(name = "status",nullable = false)
    private String status;

    @Column(name = "startDate",nullable = false)
    private LocalDate startDate;

    @Column(name = "endDate",nullable = false)
    private LocalDate endDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id",nullable = false)
    private Project project;
}
