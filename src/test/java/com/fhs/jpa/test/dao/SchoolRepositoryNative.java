package com.fhs.jpa.test.dao;

import com.fhs.jpa.anno.NativeQueryRepository;
import com.fhs.jpa.anno.ResultClass;
import com.fhs.jpa.test.pojo.SchoolDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

@NativeQueryRepository
public interface SchoolRepositoryNative {

    @ResultClass(SchoolDto.class)
    @Query(value = "select id,school_name as schoolName,remark from school where id =?1",nativeQuery = true)
    SchoolDto findById(String id);

    @ResultClass(SchoolDto.class)
    @Query(value = "select id,school_name as schoolName,remark from school where id in (:ids)",nativeQuery = true)
    List<SchoolDto> findByIds(@Param("ids") Set<String> ids);
}
