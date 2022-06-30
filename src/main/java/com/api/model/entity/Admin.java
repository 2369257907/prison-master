package com.api.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//用户类
@Data
@Entity
@NoArgsConstructor
public class Admin {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String accountName;

    private String name;

    private String password;

    private String role;

    private String phoneNumber;

    private String sex;

    private String address;

}
