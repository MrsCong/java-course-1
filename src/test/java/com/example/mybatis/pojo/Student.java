package com.example.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类描述:
 *
 * @author 吴智聪
 * @version 1.0
 * @date 2022/4/16 16:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String name;

    private int age;

    private int gender;

}