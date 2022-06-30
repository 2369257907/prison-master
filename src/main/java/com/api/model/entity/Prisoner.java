package com.api.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//犯人类
@Data
@Entity
@NoArgsConstructor
public class Prisoner {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;

    private int age;

    private String sex;

    //籍贯
    private String origin;

    //身份证号
    private String idCard;

    //罪名
    private String accusation;

    //入狱时间
    private String inTime;

    //刑期(天)
    private int sentence;

    //是否在监狱,默认为true
    private boolean isInJai=true;


}
