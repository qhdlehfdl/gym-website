package com.study.shoppingmall.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String userID;
    private String userPassword;
    private String userEmail;
    private String userName;
}
