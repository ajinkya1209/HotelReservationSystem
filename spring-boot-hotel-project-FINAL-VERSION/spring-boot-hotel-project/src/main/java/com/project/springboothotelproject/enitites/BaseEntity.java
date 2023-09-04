package com.project.springboothotelproject.enitites;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass //To share common properties and behaviors among multiple entity
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //NO ARGS CONSTRUCTOR
    public BaseEntity() {
        super();
    }

    public Long getId() {
        return Id;
    }
}
