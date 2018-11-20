package com.robcio.springstuff.entity;

import com.robcio.springstuff.enumeration.UserStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private Integer age;

    @OneToMany(mappedBy = "user")
    private Set<InventoryItem> items;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}