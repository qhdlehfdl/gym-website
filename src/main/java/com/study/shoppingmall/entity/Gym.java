package com.study.shoppingmall.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Gym {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer gymID;
    private Integer hostID;
    private String gymAddress;
    private String gymName;
    private Integer dayPrice;
}
