package com.security.demo.Security.custom;
import com.security.demo.Entity.Permission;
import com.security.demo.Entity.Role;
import com.security.demo.Entity.User;
import com.security.demo.Service.PermissionService;
import com.security.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 认证过程中 - 根据登录信息获取用户详细信息
     *
     * @param username 登录用户输入的用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户输入的用户信息，查询数据库中已注册用户信息
        User user = userService.findByName(username);
        //如果用户不存在直接抛出UsernameNotFoundException异常
        if (user == null) throw new UsernameNotFoundException("用户不存在");
        System.out.println(username);
        //声明一个用于存放用户权限的列表
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        //获取该用户所拥有的权限
        List<Permission> authority = permissionService.selectListByUserId(user.getId());
        //获取该用户所属角色
        List<Role> role = permissionService.selectRoleListByUserId(user.getId());
        //把用户所拥有的权限添加到列表中
        authority.forEach(permission -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getAuthority()));
        });
        //把用户角色加到列表中
        role.forEach(role1 -> {
            //注意：添加角色的时候要在前面加ROLE_前缀
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role1.getRole_name()));
        });

        //创建并返回User对象，注意这里的User不是我们实体类里面的User
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), grantedAuthorities );
    }
}
