package com.example.concert_service.data.model;

import javax.persistence.*;

@Entity
@Table(name = "user_actions", schema = "public")
public class UserAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actionId;
    private String name;
}
