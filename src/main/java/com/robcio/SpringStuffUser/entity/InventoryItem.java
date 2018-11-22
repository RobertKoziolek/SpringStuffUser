package com.robcio.SpringStuffUser.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.robcio.SpringStuffUser.enumeration.ItemType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    private String name;

    @Enumerated(EnumType.STRING)
    private ItemType type;

}