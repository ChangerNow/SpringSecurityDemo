package com.security.demo.Service;

import com.security.demo.Entity.Permission;
import com.security.demo.Entity.Role;
import com.security.demo.Mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> selectListByUserId(Integer id){
        return permissionMapper.selectListByUserId(id);
    }

    public List<Role> selectRoleListByUserId(Integer id){
        return permissionMapper.selectRoleListByUserId(id);
    }
}
