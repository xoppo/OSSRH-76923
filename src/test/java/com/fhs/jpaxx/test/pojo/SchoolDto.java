package com.fhs.jpaxx.test.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class SchoolDto {

    private String id;


    private String schoolName;


    private String remark;
}
