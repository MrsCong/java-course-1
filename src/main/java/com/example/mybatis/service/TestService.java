package com.example.mybatis.service;

import com.example.mybatis.po.Person;

import java.util.Date;
import java.util.List;

public interface TestService {
    String queryForList();

    void insertPerson();

    void deletePersonById(String id);

    int updatePerson(Person person);

    void batchUpdateTest();

    List<String> selectByTime();

    int updateById(Date date,String id);
}
