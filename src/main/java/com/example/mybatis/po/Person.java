package com.example.mybatis.po;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class Person {

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }
    @NotBlank(message = "id不能为空")
    private String id;

    @Size(max = 10)
    @NotBlank(message = "名称不能为空")
    private String name;

}
