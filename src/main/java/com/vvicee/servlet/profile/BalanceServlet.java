package com.vvicee.servlet.profile;

import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;
import com.vvicee.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vvicee.constant.navigation.Path.PROFILE_BALANCE_SERVLET;
import static com.vvicee.constant.navigation.Path.PROFILE_SERVLET;
import static com.vvicee.constant.servlet.UserServletsConstant.CURRENT_USER;
import static com.vvicee.constant.servlet.UserServletsConstant.MONEY;

@WebServlet(PROFILE_BALANCE_SERVLET)
public class BalanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute(CURRENT_USER);
        double balance = Double.parseDouble(req.getParameter(MONEY));

        try {
            UserService userService = new UserService();
            if (balance > 0) {
                userService.changeBalance(user, balance);
            }

        } catch (DBException exception) {
            exception.printStackTrace();
        }

        resp.sendRedirect(PROFILE_SERVLET);
    }
}
