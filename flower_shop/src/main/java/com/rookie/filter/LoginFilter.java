package com.rookie.filter;

import com.rookie.myconstant.MyConstant;
import com.rookie.pojo.Customer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoginFilter implements Filter {

    private List<String> excludeUrisList  = new ArrayList<>();

    public void init(FilterConfig config) throws ServletException {
        String excludeUris = config.getInitParameter("excludeUris");
        if (excludeUris!=null){
            String[] split = excludeUris.split(",");
            excludeUrisList = Arrays.asList(split);
        }
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //强转
        HttpServletRequest req = null;
        HttpServletResponse resp = null;
        try {
            req = (HttpServletRequest)request;
            resp = (HttpServletResponse) response;
        } catch (Exception e) {
            throw new RuntimeException("不是Http请求");
        }
        //放行排除的用户请求地址
        String requestURI = req.getRequestURI().replace(req.getContextPath(),"");
        if(excludeUrisList.contains(requestURI)){
            chain.doFilter(req,resp);
            return;
        }

        //是否登录
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute(MyConstant.loginFlag);
        if (customer!=null){
            chain.doFilter(req, resp);
            return;
        }

        //未登录时返回登录页面
        resp.sendRedirect(req.getContextPath()+"/customer.jsp");

    }



    public void destroy() {
    }


}
