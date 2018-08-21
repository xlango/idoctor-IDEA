package com.idoctor.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义shiro自定义Filter
 * 满足任意一个角色及满足条件的Filter
 */
public class RoleOrFilter extends AuthorizationFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest
            , ServletResponse servletResponse, Object o) throws Exception {
        Subject subject=getSubject(servletRequest,servletResponse);
        String[] roles= (String[]) o;
        if (roles==null || roles.length==0){
            return true;
        }
        for (String role:roles){
            if (subject.hasRole(role)){
                return true;
            }
        }
        return false;
    }
}
