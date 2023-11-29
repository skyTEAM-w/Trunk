package com.whut.truck.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import com.whut.truck.utils.unprotectedPagesLoader;

/**
 * 访问过滤器，用于控制对JSP页面的访问权限。
 */
@WebFilter(value = "*.jsp", filterName = "LoginAccessFilter")
public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * 执行过滤操作，检查用户是否有权访问请求的JSP页面。
     *
     * @param servletRequest  请求对象
     * @param servletResponse 响应对象
     * @param filterChain     过滤器链
     * @throws IOException      如果发生I/O错误
     * @throws ServletException 如果发生Servlet错误
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // 获取会话对象
        HttpSession httpSession = httpServletRequest.getSession();
        String requestURI = httpServletRequest.getRequestURI();
//        System.out.println(requestURI);

        // 获取非受保护页面列表
        List<String> unprotectedPages = unprotectedPagesLoader.getUnprotectedPages(httpServletRequest.getContextPath());

        // 检查用户是否已登录或请求的页面为非受保护页面，允许访问
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

    /**
     * 检查用户是否已登录。
     *
     * @param session 会话对象
     * @return 如果用户已登录则返回true，否则返回false。
     */
    private boolean isUserLoggedIn(HttpSession session) {
        // 从 HttpSession 中检查用户是否已登录
        return session.getAttribute("systemAdmin") != null;
    }
}

