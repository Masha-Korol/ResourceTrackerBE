package com.example.concert_service.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments", schema = "public")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private Date date;
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
