package com.fhs.jpa.test.dao;

import com.fhs.jpa.test.pojo.School;
import com.fhs.jpa.test.pojo.SchoolQueryPayload;
import com.fhs.jpa.wrapper.LambdaQueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolRepositoryTest {
    @Autowired
    private SchoolRepository schoolRepository;

    @Test
    public void test(){
        System.out.println(schoolRepository.findAll(new LambdaQueryWrapper<School>().or(wrapper->{
            wrapper.eq(School::getId,1);
            wrapper.eq(School::getId,2);
        }).like(School::getSchoolName,"一")
                .orderByAsc(School::getId).build()));
    }

    @Test
    public void testQueryWrapper(){
        /*
          1 SchoolQueryPayload模拟前端传过来的参数
          2 merge 一个LambdaQueryWrapper 模拟部分后端需要拼接的条件，比如租户id  用户id之类的
         */
        System.out.println(schoolRepository.findAll(SchoolQueryPayload.builder().schoolName("一").remarkOrName("2").build().asWrapper()
                .merge(new LambdaQueryWrapper<School>().eq(School::getId,1)).build()));
    }

   /* @Test
    public void test2(){
        System.out.println(schoolRepository.findSchoolVO(1));
    }*/
}
