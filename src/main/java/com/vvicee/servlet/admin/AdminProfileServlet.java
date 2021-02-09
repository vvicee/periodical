package com.vvicee.servlet.admin;

import com.vvicee.exception.DBException;
import com.vvicee.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vvicee.constant.navigation.Path.*;
import static com.vvicee.constant.servlet.AdminServletsConstants.ACTIVE_USERS;
import static com.vvicee.constant.servlet.AdminServletsConstants.BLOCKED_USERS;

@WebServlet(ADMIN_PROFILE_SERVLET)
public class AdminProfileServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(AdminProfileServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = new UserService();

            req.setAttribute(ACTIVE_USERS, userService.getActiveUsers());
            req.setAttribute(BLOCKED_USERS, userService.getBlockedUsers());

            req.getRequestDispatcher(ADMIN_PAGE).forward(req, resp);
        } catch (DBException e) {
            logger.error("Failed get users for admin profile");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }

}
