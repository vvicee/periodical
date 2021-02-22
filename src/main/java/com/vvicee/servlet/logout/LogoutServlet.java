package com.vvicee.servlet.logout;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vvicee.constant.navigation.Path.HOME_SERVLET;
import static com.vvicee.constant.navigation.Path.LOGOUT_SERVLET;
import static com.vvicee.constant.servlet.ErrorsConstant.ERRORS;
import static com.vvicee.constant.servlet.UserServletsConstant.CURRENT_USER;
import static java.util.Objects.nonNull;

@WebServlet(LOGOUT_SERVLET)
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(nonNull(req.getSession(false).getAttribute(CURRENT_USER))){
            req.getSession().removeAttribute(ERRORS);
            req.getSession().removeAttribute(CURRENT_USER);
        }
        resp.sendRedirect(HOME_SERVLET);
    }
}
