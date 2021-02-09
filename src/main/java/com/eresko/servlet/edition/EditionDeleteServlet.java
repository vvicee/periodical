package com.eresko.servlet.edition;

import com.eresko.db.dao.EditionDAO;
import com.eresko.db.implDao.EditionDAOImpl;
import com.eresko.entity.edition.Edition;
import com.eresko.exception.DBException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.eresko.constant.entity.EditionConstant.EDITION_ID;
import static com.eresko.constant.navigation.Path.*;

@WebServlet(EDITION_DELETE_SERVLET)
public class EditionDeleteServlet extends HttpServlet {

    public static final Logger logger = Logger.getLogger(EditionDeleteServlet.class);
    EditionDAO editionDAO;

    @Override
    public void init() throws ServletException {
        editionDAO = new EditionDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(EDITION_ID));

        try {
            Edition edition = editionDAO.find(id);
            logger.debug("Delete edition " + edition);
            editionDAO.delete(edition);
        } catch (DBException e) {
            logger.error("Failed delete edition");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
        logger.debug("Edition delete successfully");
        resp.sendRedirect(EDITION_DESCRIPTION_SERVLET + "?edition_id=" + id);
    }
}
