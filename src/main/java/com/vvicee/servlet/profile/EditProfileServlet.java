package com.vvicee.servlet.profile;

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
import java.io.IOException;

import static com.vvicee.constant.entity.UserConstant.*;
import static com.vvicee.constant.navigation.Path.*;
import static com.vvicee.constant.servlet.UserServletsConstant.CURRENT_USER;

@WebServlet(PROFILE_EDIT_SERVLET)
public class EditProfileServlet extends HttpServlet {

    Logger logger = Logger.getLogger(EditProfileServlet.class);
    UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(CURRENT_USER);
        req.setAttribute(CURRENT_USER, user);

        req.getRequestDispatcher(PROFILE_EDIT_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(CURRENT_USER);

        user.setName(req.getParameter(USER_NAME));
        user.setSurname(req.getParameter(USER_SURNAME));
        user.setMailings(req.getParameter(USER_NOTIFY).equals("yes"));

        try {
            userDAO.update(user);
        } catch ( DBException e) {
            logger.error("Failed servlet in post request");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
        resp.sendRedirect(PROFILE_SERVLET);
    }

}
