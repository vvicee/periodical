package com.vvicee.servlet.edition;

import com.vvicee.db.dao.EditionDAO;
import com.vvicee.db.implDao.EditionDAOImpl;
import com.vvicee.entity.edition.Edition;
import com.vvicee.exception.DBException;
import com.vvicee.service.SubscriptionService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vvicee.constant.entity.EditionConstant.EDITION_ID;
import static com.vvicee.constant.navigation.Path.*;
import static com.vvicee.constant.servlet.EditionServletConstant.SUBSCRIPTIONS;
import static com.vvicee.constant.servlet.ErrorsConstant.EDITION_IN_SUBSCRIPTION_ERROR;

@WebServlet(EDITION_DELETE_SERVLET)
public class EditionDeleteServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EditionDeleteServlet.class);
    private EditionDAO editionDAO;
    private SubscriptionService subscriptionService;

    @Override
    public void init() throws ServletException {
        editionDAO = new EditionDAOImpl();
        subscriptionService = new SubscriptionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(EDITION_ID));

        try {
            Edition edition = editionDAO.find(id);
            if (!subscriptionService.editionInSubscription(edition)) {
                logger.debug("Delete edition " + edition);
                editionDAO.delete(edition);
                logger.debug("Edition delete successfully");
                resp.sendRedirect(HOME_SERVLET);
                return;
            }
            req.setAttribute(SUBSCRIPTIONS, EDITION_IN_SUBSCRIPTION_ERROR);
        } catch (DBException e) {
            logger.error("Failed delete edition");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
        resp.sendRedirect(EDITION_DESCRIPTION_SERVLET + "?edition_id=" + id);
    }
}
