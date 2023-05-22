package com.deng.springbootmodule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deng.springbootmodule.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
