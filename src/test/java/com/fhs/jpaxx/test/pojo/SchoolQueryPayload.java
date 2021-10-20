package com.fhs.jpaxx.test.pojo;

import com.fhs.jpa.anno.Eq;
import com.fhs.jpa.anno.GroupOrLike;
import com.fhs.jpa.anno.Like;
import com.fhs.jpa.pojo.Wrapperble;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SchoolQueryPayload implements Wrapperble {

    @Eq
    private Integer id;

    @Like
    private String schoolName;

    @GroupOrLike(fields = {"schoolName","remark"})
    private String remarkOrName;

}
