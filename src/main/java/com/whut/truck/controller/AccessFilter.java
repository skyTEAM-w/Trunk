package com.whut.truck.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.whut.truck.utils.unprotectedPagesLoader;

@WebFilter(value = "*.jsp", filterName = "LoginAccessFilter")
public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //回话获取
        HttpSession httpSession = httpServletRequest.getSession();
        String requestURI = httpServletRequest.getRequestURI();
//        System.out.println(requestURI);
        List<String> unprotectedPages = unprotectedPagesLoader.getUnprotectedPages(httpServletRequest.getContextPath());

        if (requestURI.endsWith("Login.jsp") || isUserLoggedIn(httpSession) || unprotectedPages.contains(requestURI)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 否则，重定向到登录页面
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/Login.jsp");
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean isUserLoggedIn(HttpSession session) {
        // 从 HttpSession 中检查用户是否已登录
        return session.getAttribute("systemAdmin") != null;
    }
}
