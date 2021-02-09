package com.vvicee.servlet.edition;

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
import static com.vvicee.constant.entity.EditionConstant.EDITION_ID;
import static com.vvicee.constant.navigation.Path.*;
import static com.vvicee.constant.servlet.EditionServletConstant.EDITION;

@WebServlet(EDITION_EDIT_SERVLET)
public class EditionEditServlet extends HttpServlet {

    public static final Logger logger = Logger.getLogger(EditionEditServlet.class);
    EditionDAO editionDAO;
    EditionService editionService;
    @Override
    public void init() throws ServletException {
        editionDAO = new EditionDAOImpl();
        editionService = (EditionService) getServletContext().getAttribute(EDITION_SERVICE_CONTEXT);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter(EDITION_ID));

        try {
            Edition edition = editionDAO.find(id);
            req.setAttribute(EDITION, edition);
            req.getRequestDispatcher(EDITION_EDIT_PAGE).forward(req, resp);

        } catch (DBException e) {
            logger.error("Failed servlet in get request " + EditionEditServlet.class);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(EDITION_ID));

        try {
            Edition edition = editionDAO.find(id);
            logger.debug("Update edition " + edition);
            editionService.setParametersOfEdition(req, edition);
            editionDAO.update(edition);
        } catch (DBException e) {
            logger.error("Failed servlet in post request" + EditionEditServlet.class );
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

        logger.debug("Edition update successfully");
        resp.sendRedirect(EDITION_DESCRIPTION_SERVLET + "?edition_id=" + id);

    }
}
