package com.eresko.servlet.registration;

import com.eresko.db.dao.UserDAO;
import com.eresko.db.implDao.UserDAOImpl;
import com.eresko.entity.user.User;
import com.eresko.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.eresko.constant.entity.UserConstant.*;
import static com.eresko.constant.navigation.Path.*;

@WebServlet(SIGN_UP_SERVLET)
public class SignUpServlet extends HttpServlet {

    private UserDAO userDAO;
    private final Logger logger = Logger.getLogger(SignUpServlet.class);

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTRATION_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        User user = new User();

        user.setName(req.getParameter(USER_NAME));
        user.setSurname(req.getParameter(USER_SURNAME));
        user.setMailings(req.getParameter(USER_NOTIFY).equals("Yes"));
        user.setPassword(req.getParameter(USER_PASSWORD));
        user.setEmail(req.getParameter(USER_EMAIL));

        try {
            logger.debug("Adding user " + user);
            userDAO.add(user);
        } catch (DBException e) {
            logger.error("Failed servlets in post request");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
        logger.debug("User added successfully");
        resp.sendRedirect(SIGN_UP_SERVLET);
    }
}
