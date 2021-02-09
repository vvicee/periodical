package com.vvicee.servlet.profile;

import com.vvicee.db.dao.UserDAO;
import com.vvicee.db.implDao.UserDAOImpl;
import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;
import com.vvicee.service.SubscriptionService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vvicee.constant.navigation.Path.*;
import static com.vvicee.constant.servlet.EditionServletConstant.EDITION;
import static com.vvicee.constant.servlet.UserServletsConstant.CURRENT_USER;

@WebServlet(PROFILE_SERVLET)
public class ProfileServlet extends HttpServlet {

    public static final Logger logger = Logger.getLogger(ProfileServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubscriptionService subscriptionService = new SubscriptionService();
        User user = (User) req.getSession().getAttribute(CURRENT_USER);

        req.setAttribute(CURRENT_USER, user);
        try {
            req.setAttribute(EDITION, subscriptionService.getEditions(user));
            req.setAttribute("subscriptions", subscriptionService.getSubscriptions(user.getId()));
        } catch (DBException e) {
            logger.error("Failed in get request");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
        req.getRequestDispatcher(PROFILE_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAOImpl();
        try {
            User user = (User) req.getSession().getAttribute(CURRENT_USER);
            logger.debug("Delete user " + user);
            userDAO.delete(user);
        } catch (DBException e) {
            logger.error("Failed in post request");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
        logger.debug("User deleted successfully");
        resp.sendRedirect(LOGOUT_SERVLET);
    }
}
