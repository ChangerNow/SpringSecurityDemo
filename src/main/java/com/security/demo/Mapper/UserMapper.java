package com.security.demo.Mapper;

import com.security.demo.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "UserMapper")
public interface UserMapper {

    User findByName( String username);

}
