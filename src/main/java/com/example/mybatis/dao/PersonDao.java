package com.example.mybatis.dao;

import com.example.mybatis.po.Person;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PersonDao {

    List<Person> queryForList();

    void insertPerson(Person person);

    void deletePersonById(String id);

    int updatePerson(Person person);

    void batchUpdateTest(List<String> ids);

    List<String> selectByTime(@Param(value = "time") Date date);

    int updateById(@Param("time") Date date,@Param("id") String id);
}
