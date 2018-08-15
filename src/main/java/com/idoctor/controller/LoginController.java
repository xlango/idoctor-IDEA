package com.idoctor.controller;

import com.idoctor.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/subLogin",method = RequestMethod.POST
    ,produces="text/html;charset=UTF-8")
    public String subLogin(User user){
        System.out.println(user.getName()+" : "+user.getPassword());
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getName(),user.getPassword());

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }

        if (subject.hasRole("admin")){
            return "有admin权限";
        }
        return "无admin权限";
    }

    @RequiresRoles("admin")
    //@RequiresPermissions("user:add")
    @RequestMapping(value = "/testRole",method = RequestMethod.GET)
    public String testRole(){
        return "testRole success";
    }

    @RequiresRoles("admin1")
    @RequestMapping(value = "/testRole1",method = RequestMethod.GET)
    public String testRole1(){
        return "testRole1 success";
    }
}
