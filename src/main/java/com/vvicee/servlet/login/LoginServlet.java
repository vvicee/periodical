package com.vvicee.servlet.login;

import com.vvicee.db.dao.UserDAO;
import com.vvicee.db.implDao.UserDAOImpl;
import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.vvicee.constant.entity.UserConstant.USER_EMAIL;
import static com.vvicee.constant.entity.UserConstant.USER_PASSWORD;
import static com.vvicee.constant.navigation.Path.*;
import static com.vvicee.constant.servlet.UserServletsConstant.CURRENT_USER;

@WebServlet(LOGIN_SERVLET)
public class LoginServlet extends HttpServlet {

    public static final Logger logger = Logger.getLogger(LoginServlet.class);
    UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(USER_EMAIL);
        String password = req.getParameter(USER_PASSWORD);

        logger.debug("Login user with email = " + email + "and password = " + password);
        try {


            if (userDAO.isExist(email, password)) {
                User user = userDAO.findByEmail(email);
                HttpSession session = req.getSession(true);
                if (session.getAttribute(CURRENT_USER) == null) {
                    session.setAttribute(CURRENT_USER, user);
                }
                session.setMaxInactiveInterval(24 * 60 * 60);
                resp.sendRedirect(HOME_SERVLET);
                logger.debug("User found");
            } else {
                logger.debug("User not found");
                resp.sendRedirect(LOGIN_SERVLET);
            }
        } catch (DBException e) {
            logger.error("Failed servlet in post request");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
