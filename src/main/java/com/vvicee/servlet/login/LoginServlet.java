package com.vvicee.servlet.login;

import com.vvicee.db.dao.UserDAO;
import com.vvicee.db.implDao.UserDAOImpl;
import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;
import com.vvicee.util.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.vvicee.constant.context.ContextConstant.REQUEST_PAGE;
import static com.vvicee.constant.entity.UserConstant.*;
import static com.vvicee.constant.navigation.Path.*;
import static com.vvicee.constant.servlet.ErrorsConstant.*;
import static com.vvicee.constant.servlet.UserServletsConstant.CURRENT_USER;
import static java.util.Objects.nonNull;

@WebServlet(LOGIN_SERVLET)
public class LoginServlet extends HttpServlet {

    public static final Logger logger = Logger.getLogger(LoginServlet.class);
    private UserDAO userDAO;
    private Map<String, String> errors;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors = (Map<String, String>) req.getSession().getAttribute(ERRORS);
        if (nonNull(errors)) {
            errors.forEach(req::setAttribute);
        }
        req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors = new HashMap<>();

        String email = req.getParameter(USER_EMAIL);
        String password = req.getParameter(USER_PASSWORD);
        User user;

        logger.debug("Login user with email = " + email);
        try {

            if ((user = userDAO.findByEmail(email)) == null) {
                errors.put(USER_EMAIL, UNREGISTERED_USER_ERROR);
            } else if (!Validator.comparePasswords(user.getPassword(), password)) {
                errors.put(USER_PASSWORD, INCORRECT_PASSWORD_ERROR);
            } else if(!user.isActive()){
                errors.put(USER_ACTIVE, NOT_ACTIVATED_ERROR);
            }

            if (errors.isEmpty()) {
                req.getSession().setAttribute(CURRENT_USER, user);
                logger.debug("User found");
                redirectToPage(req, resp);
                return;
            }

            req.getSession().setAttribute(ERRORS, errors);
            logger.debug("User not found");
            resp.sendRedirect(LOGIN_SERVLET);

        } catch (DBException e) {
            logger.error("Failed servlet in post request");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }

    /**
     * @param req  - {@link HttpServletRequest}
     * @param resp - {@link HttpServletResponse}
     * @throws IOException - req.getRequestDispatcher.forward() throws
     */
    private void redirectToPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestedPage = (String) req.getSession().getAttribute(REQUEST_PAGE);

        if (requestedPage != null) {
            req.getSession().setAttribute(REQUEST_PAGE, null);
            resp.sendRedirect(requestedPage);
        } else {
            resp.sendRedirect(HOME_SERVLET);
        }
    }
}
