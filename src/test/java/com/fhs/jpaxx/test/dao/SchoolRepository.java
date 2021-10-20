package com.fhs.jpaxx.test.dao;

import com.fhs.jpa.anno.ResultClass;
import com.fhs.jpaxx.test.pojo.School;
import com.fhs.jpaxx.test.pojo.SchoolDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SchoolRepository extends JpaRepository<School, String>, JpaSpecificationExecutor<School> {
    @ResultClass(SchoolDto.class)
    @Query(value = "select id,school_name as schoolName,remark from school where id =?1",nativeQuery = true)
    SchoolDto findByIdXX(String id);

    @ResultClass(SchoolDto.class)
    @Query(value = "select id,school_name as schoolName,remark from school where id in (:ids)",nativeQuery = true)
    List<SchoolDto> findByIds(@Param("ids") Set<String> ids);
}
