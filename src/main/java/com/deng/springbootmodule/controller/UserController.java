package com.deng.springbootmodule.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deng.springbootmodule.config.Result;
import com.deng.springbootmodule.entity.User;
import com.deng.springbootmodule.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @PostMapping
    public Result<?> save(@RequestBody User user){
        if(user.getPassword()==null){
            user.setPassword("123456");
        }
        userMapper.insert(user);

        return Result.success();
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword()));
        if(res == null){
            return Result.error("-1","错误");
        }
        return Result.success(res);
    }
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if(res != null){
            return Result.error("-1","用户名重复");
        }
        if(user.getPassword() == null){
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody User user){
        userMapper.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        userMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if(StrUtil.isNotEmpty(search)){//为了避免null查不出来
//            这里查的是昵称
            wrapper.like(User::getNickName, search);
        }
        Page<User> page = userMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);
        return Result.success(page);
    }
}
