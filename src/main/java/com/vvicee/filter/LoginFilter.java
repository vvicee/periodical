package com.vvicee.filter;

import com.vvicee.entity.user.User;
import com.vvicee.service.EditionService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vvicee.constant.context.ContextConstant.EDITION_SERVICE_CONTEXT;
import static com.vvicee.constant.context.ContextConstant.REQUEST_PAGE;
import static com.vvicee.constant.navigation.Path.LOGIN_SERVLET;
import static com.vvicee.constant.servlet.UserServletsConstant.CURRENT_USER;

public class LoginFilter implements Filter {
    EditionService service;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        service = (EditionService) req.getServletContext().getAttribute(EDITION_SERVICE_CONTEXT);
        service.resetFilters();

        String url = req.getRequestURL().toString();
        User user = (User) req.getSession().getAttribute(CURRENT_USER);
        if (user == null) {
            req.getSession().setAttribute(REQUEST_PAGE, url);
            resp.sendRedirect(LOGIN_SERVLET);
            return;
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
