package com.vvicee.servlet.admin;

import com.vvicee.db.implDao.UserDAOImpl;
import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;
import com.vvicee.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.vvicee.constant.entity.UserConstant.USER_ACTIVE;
import static com.vvicee.constant.entity.UserConstant.USER_ID;
import static com.vvicee.constant.navigation.Path.*;
import static com.vvicee.constant.servlet.AdminServletsConstants.*;

@WebServlet(ADMIN_USERS)
public class UsersServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(UsersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = new UserService();
            int page = Integer.parseInt(req.getParameter(PAGE));
            int maxPage = userService.getMaxNumberPage();

            if (page >= maxPage) {
                page = maxPage;
            } else if (page < maxPage) {
                page = 1;
            }

            req.setAttribute(PAGE, page);
            req.setAttribute(MAX_PAGE, maxPage);

            req.setAttribute(USERS, userService.getUsers(page));
            req.getRequestDispatcher(USERS_PAGE).forward(req, resp);

        } catch (DBException e) {
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAOImpl userDAO = new UserDAOImpl();

        int id = Integer.parseInt(req.getParameter(USER_ID));
        boolean active = Boolean.parseBoolean(req.getParameter(USER_ACTIVE));

        try {
            User user = userDAO.find(id);
            logger.debug("Change active for user with id= " + user.getId() + ". Active = " + active);
            user.setActive(active);
            userDAO.update(user);
        } catch (DBException exception) {
            logger.error("Failed change active" );
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

        resp.sendRedirect(ADMIN_USERS + "?page=1");

    }

}
