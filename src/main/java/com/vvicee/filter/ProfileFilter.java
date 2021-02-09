package com.vvicee.filter;

import com.vvicee.entity.user.Role;
import com.vvicee.entity.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vvicee.constant.navigation.Path.ACCESS_ERROR_PAGE;
import static com.vvicee.constant.servlet.UserServletsConstant.CURRENT_USER;

public class ProfileFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User user = (User) req.getSession().getAttribute(CURRENT_USER);
        if (!user.isActive() || user.getRole().equals(Role.ADMIN)) {
            req.getRequestDispatcher(ACCESS_ERROR_PAGE).forward(req, resp);
            return;
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }
}
