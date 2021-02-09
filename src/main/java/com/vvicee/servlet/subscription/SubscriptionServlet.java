package com.vvicee.servlet.subscription;

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

import static com.vvicee.constant.entity.SubscriptionConstant.EDITION_ID_FROM_SUBSCRIPTION;
import static com.vvicee.constant.entity.SubscriptionConstant.SUBSCRIPTION_MONTHS;
import static com.vvicee.constant.navigation.Path.*;
import static com.vvicee.constant.servlet.UserServletsConstant.CURRENT_USER;

@WebServlet(SUBSCRIPTION_SERVLET)
public class SubscriptionServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(SubscriptionServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubscriptionService subscriptionService = new SubscriptionService();

        int editionId = Integer.parseInt(req.getParameter(EDITION_ID_FROM_SUBSCRIPTION));
        String[] months = req.getParameterValues(SUBSCRIPTION_MONTHS);
        User user = (User) req.getSession().getAttribute(CURRENT_USER);

        try {
            if (subscriptionService.createSubscription(user, editionId, months)) {
                //errors
            }

        } catch (DBException e) {
            logger.error("Failed in post request");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
        resp.sendRedirect(PROFILE_SERVLET);
    }
}
