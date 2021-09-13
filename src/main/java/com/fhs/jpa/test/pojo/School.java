package com.fhs.jpa.test.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="school")
public class School {
    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="school_name")
    private String schoolName;
}
