package com.eresko.servlet.edition;

import com.eresko.db.dao.EditionDAO;
import com.eresko.db.implDao.EditionDAOImpl;
import com.eresko.entity.edition.Edition;
import com.eresko.entity.subscription.Month;
import com.eresko.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.eresko.constant.entity.EditionConstant.EDITION_ID;
import static com.eresko.constant.navigation.Path.EDITION_DESCRIPTION_SERVLET;
import static com.eresko.constant.navigation.Path.EDITION_PAGE;
import static com.eresko.constant.servlet.EditionServletConstant.EDITION;
import static com.eresko.constant.servlet.EditionServletConstant.MONTHS;

@WebServlet(EDITION_DESCRIPTION_SERVLET)
public class EditionDescriptionServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EditionDescriptionServlet.class);
    EditionDAO editionDAO;

    @Override
    public void init() throws ServletException {
        editionDAO = new EditionDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int editionId = Integer.parseInt(req.getParameter(EDITION_ID));

        try {
            Edition edition = editionDAO.find(editionId);
            req.setAttribute(EDITION, edition);
            req.setAttribute(MONTHS, Month.getAvailableMonths());

            req.getRequestDispatcher(EDITION_PAGE).forward(req, resp);

        } catch (DBException e) {
            logger.error("Failed servlet " + EditionDescriptionServlet.class);
        }
    }
}
