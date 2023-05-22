package com.deng.springbootmodule.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("user")
@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private Integer age;
    private String sex;
    private String address;
//    private String avatar;
//    @TableField(exist = false)
//    private List<Integer> roles;
//
//    @TableField(exist = false)
//    private List<Book> bookList;
//
//    @TableField(exist = false)
//    private String token;
//
//    private BigDecimal account;
//
//    @TableField(exist = false)
//    private Set<Permission> permissions;
}
