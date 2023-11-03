package com.study.shoppingmall.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Machine {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer part;
    private String brand;
    private Integer price;
    private String fileName;
    private String filePath;
    private String title;
    private String content;
}
