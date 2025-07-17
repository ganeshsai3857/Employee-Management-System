package com.gl.ems.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gl.ems.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="department")
@Getter
@Setter
@NoArgsConstructor
public class Department {
//    id,name,listOfEmployees

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;


//    @JoinColumn(name = "department_id",nullable = false)
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Project>projects ;
}
