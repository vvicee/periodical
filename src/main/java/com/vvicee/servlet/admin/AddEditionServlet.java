package com.vvicee.servlet.admin;

import com.vvicee.db.dao.EditionDAO;
import com.vvicee.db.implDao.EditionDAOImpl;
import com.vvicee.entity.edition.Edition;
import com.vvicee.exception.DBException;
import com.vvicee.service.EditionService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vvicee.constant.context.ContextConstant.EDITION_SERVICE_CONTEXT;
import static com.vvicee.constant.navigation.Path.*;

@WebServlet(EDITION_ADD_SERVLET)
public class AddEditionServlet extends HttpServlet {

    private EditionDAO editionDAO;
    private final Logger logger = Logger.getLogger(AddEditionServlet.class);

    @Override
    public void init() throws ServletException {
        editionDAO = new EditionDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Edition edition = new Edition();
            EditionService editionService = (EditionService) getServletContext().getAttribute(EDITION_SERVICE_CONTEXT);

            editionService.setParametersOfEdition(req, edition);
            logger.debug("Add new edition " + edition);
            editionDAO.add(edition);

        } catch (DBException e) {
            logger.error("Failed add edition in servlet " + AddEditionServlet.class);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
        logger.debug("Edition added successfully");

        resp.sendRedirect(ADMIN_PROFILE_SERVLET);
    }

}
