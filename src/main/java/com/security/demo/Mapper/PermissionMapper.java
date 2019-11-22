package com.security.demo.Mapper;

import com.security.demo.Entity.Permission;
import com.security.demo.Entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "PermissionMapper")
public interface PermissionMapper {

    List<Permission> selectListByUserId(Integer id);
    List<Role> selectRoleListByUserId(Integer id);
}
