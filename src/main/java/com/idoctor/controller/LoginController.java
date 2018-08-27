package com.idoctor.controller;

import com.idoctor.pojo.JSONResult;
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

    @RequestMapping(value = "/subLogin",method = RequestMethod.POST) //,produces="text/html;charset=UTF-8"
    public JSONResult subLogin(User user){
        System.out.println(user.getUsername()+" : "+user.getPassword());
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());

        try {
            //自动登录
            token.setRememberMe(user.isRememberMe());
            subject.login(token);
        } catch (AuthenticationException e) {
            return JSONResult.errorMsg(e.getMessage());
        }

        if (subject.hasRole("1")){
            return JSONResult.ok("patient");
        }else if (subject.hasRole("2")){
            return JSONResult.ok("doctor");
        }else return JSONResult.ok("admin");
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/testRole",method = RequestMethod.GET)
    public String testRole(){
        return "testRole success";
    }

    @RequiresRoles("admin1")
    @RequestMapping(value = "/testRole1",method = RequestMethod.GET)
    public String testRole1(){
        return "testRole1 success";
    }

    @RequiresPermissions("user:add")
    @RequestMapping(value = "/testperm",method = RequestMethod.GET)
    public String testperm(){
        return "testperm success";
    }
}
