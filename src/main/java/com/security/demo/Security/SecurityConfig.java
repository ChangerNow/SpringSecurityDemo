package com.security.demo.Security;

import com.security.demo.Security.custom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启基于方法的安全认证机制

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailHandler customAuthenticationFailHandler;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //将自定的CustomUserDetailsService装配到AuthenticationManagerBuilder
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new CustomPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                /*匿名请求：不需要进行登录拦截的url*/
                .authorizeRequests()
                .antMatchers("/hello").permitAll() //允许任何人访问
                .antMatchers("/admin").hasRole("admin")//当用户的角色是为admin时可以访问这个目录
                .antMatchers("/getUser").hasAuthority("select") //当用户具有select权限时才可以访问这个方法
                .anyRequest().authenticated()//其他的路径都是登录后才可访问
                .and()
                /*登录配置*/
                .formLogin()
                .loginPage("/login_page")//登录页，当未登录时会重定向到该页面
                .successHandler(customAuthenticationSuccessHandler)//登录成功处理
                .failureHandler(customAuthenticationFailHandler)//登录失败处理
                .loginProcessingUrl("/login")//restful登录请求地址
                .usernameParameter("username")//默认的用户名参数
                .passwordParameter("password")//默认的密码参数
                .permitAll()
                .and()
                /*登出配置*/
                .logout()
                .permitAll()
                .logoutSuccessHandler(customLogoutSuccessHandler) //退出处理
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)  //无权限时的处理
                .and()
                .cors() //跨域
                .and()
                //关闭csrf防护，类似于防火墙，不关闭上面的设置不会真正生效。
                .csrf().disable();
    }

    //密码加密配置
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

}

