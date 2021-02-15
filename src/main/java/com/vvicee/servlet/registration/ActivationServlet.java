package com.vvicee.servlet.registration;

import com.vvicee.exception.DBException;
import com.vvicee.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.vvicee.constant.navigation.Path.*;

@WebServlet(ACTIVATION_SERVLET)
public class ActivationServlet extends HttpServlet {
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        try{
            userService = new UserService();
            boolean isActivate = userService.activateUser(code);
            if (isActivate) {
                req.setAttribute("message", "User successfully activated");
            } else {
                req.setAttribute("message", "Activation code is not found");
            }
            resp.sendRedirect(LOGIN_SERVLET);
        } catch (DBException exception){
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
