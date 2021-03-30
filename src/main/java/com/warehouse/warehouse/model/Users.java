package com.warehouse.warehouse.model;


import javax.persistence.*;


@Entity
public class Users {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
