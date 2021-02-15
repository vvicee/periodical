package com.vvicee.servlet.registration;

import com.vvicee.db.dao.UserDAO;
import com.vvicee.db.implDao.UserDAOImpl;
import com.vvicee.entity.user.User;
import com.vvicee.exception.DBException;
import com.vvicee.service.MailService;
import com.vvicee.service.UserService;
import com.vvicee.servlet.Validator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.vvicee.constant.entity.UserConstant.*;
import static com.vvicee.constant.navigation.Path.*;
import static com.vvicee.constant.servlet.ErrorsConstant.*;
import static com.vvicee.constant.servlet.MailConstant.ACTIVATION_CODE;

@WebServlet(SIGN_UP_SERVLET)
public class SignUpServlet extends HttpServlet {

    private UserDAO userDAO;
    private UserService userService;
    private final Logger logger = Logger.getLogger(SignUpServlet.class);
    private Map<String, String> errors;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors = (Map<String, String>) req.getSession().getAttribute(ERRORS);
        if (errors != null) {
            errors.forEach(req::setAttribute);
        }
        req.getRequestDispatcher(REGISTRATION_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        errors = new HashMap<>();
        User user;

        String name = req.getParameter(USER_NAME);
        String surname = req.getParameter(USER_SURNAME);
        String password = req.getParameter(USER_PASSWORD);
        String mail = req.getParameter(USER_NOTIFY);
        String email = req.getParameter(USER_EMAIL);

        if (!Validator.validateName(name, surname)) {
            errors.put(USER_NAME, INCORRECT_NAME_ERROR);
        } else if (!Validator.validatePassword(password)) {
            errors.put(USER_PASSWORD, INCORRECT_PASSWORD_LENGTH_ERROR);
        } else if (!Validator.validateEmail(email)) {
            errors.put(USER_EMAIL, INCORRECT_EMAIL_ERROR);
        }

        try {
            userService = new UserService();
            if (userDAO.findByEmail(email) != null) {
                errors.put(USER_EMAIL, USER_ALREADY_EXISTS_ERROR);
            }

            if (errors.isEmpty()) {
                user = new User.Builder()
                        .setName(name)
                        .setSurname(surname)
                        .setPassword(password)
                        .setMailings(mail.equals("Yes"))
                        .setEmail(email)
                        .setActive(false)
                        .setActivationCode(UUID.randomUUID().toString())
                        .build();
                logger.debug("Adding user " + user);
                userDAO.add(user);
                userService.sendActivationCode(user);
                logger.debug("User added successfully");
                resp.sendRedirect(LOGIN_SERVLET);
                return;
            }

        } catch (DBException e) {
            logger.error("Failed servlets in post request");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

        req.getSession().setAttribute(ERRORS, errors);
        resp.sendRedirect(SIGN_UP_SERVLET);
    }
}
