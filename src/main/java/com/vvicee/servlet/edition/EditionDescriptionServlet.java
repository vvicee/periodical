package com.vvicee.servlet.edition;

import com.vvicee.db.dao.EditionDAO;
import com.vvicee.db.implDao.EditionDAOImpl;
import com.vvicee.entity.edition.Edition;
import com.vvicee.entity.subscription.Month;
import com.vvicee.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vvicee.constant.entity.EditionConstant.EDITION_ID;
import static com.vvicee.constant.navigation.Path.EDITION_DESCRIPTION_SERVLET;
import static com.vvicee.constant.navigation.Path.EDITION_PAGE;
import static com.vvicee.constant.servlet.EditionServletConstant.*;

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
