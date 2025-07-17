package com.gl.ems.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gl.ems.dto.DepartmentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project")
@Getter
@Setter
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false,unique = true)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id",nullable = false)
    @JsonIgnore
    private Department department;


}
