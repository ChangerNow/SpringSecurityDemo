package com.security.demo.Controller;

import com.security.demo.Entity.User;
import com.security.demo.Security.utils.Response;
import com.security.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public Response hello(){
        return new Response("200","hello!");
    }

    //该方法我们在security配置类中指定了admin角色才可以访问
    //当然也可以直接添加@PreAuthorize("hasRole('admin')")
    @RequestMapping("/admin")
    @ResponseBody
    public Response admin(){
        return new Response("200","admin!");
    }
    //当用户具有select权限时才可以访问该方法
    @PreAuthorize("hasAuthority('select')")
    @RequestMapping("/select")
    @ResponseBody
    public Response select(){
        return new Response("200","select");
    }
    //当用户具有insert权限时才可以访问该方法
    @PreAuthorize("hasAuthority('insert')")
    @RequestMapping("/insert")
    @ResponseBody
    public Response insert(){
        return new Response("200","insert");
    }
    //当用户具有update权限时才可以访问该方法
    @PreAuthorize("hasAuthority('update')")
    @RequestMapping("/update")
    @ResponseBody
    public Response update(){
        return new Response("200","update");
    }
    //如果访问需要登录的接口，如果用户还没登录就会跳转到这个接口
    @RequestMapping("/login_page")
    @ResponseBody
    public Response root(){
        Response response = new Response("-200","未登录！");
        return response;
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(){
        //获取我们正在登陆的用户信息
        //注意这里的User是security的
        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //这里的User才是我们实体类里面的
        User user = userService.findByName(userDetails.getUsername());
        return user;
    }
}
