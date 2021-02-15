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
import java.util.HashMap;
import java.util.Map;

import static com.vvicee.constant.context.ContextConstant.EDITION_SERVICE_CONTEXT;
import static com.vvicee.constant.navigation.Path.*;
import static com.vvicee.constant.servlet.EditionServletConstant.EDITION;
import static com.vvicee.constant.servlet.ErrorsConstant.EDITION_ALREADY_EXISTS_ERROR;
import static com.vvicee.constant.servlet.ErrorsConstant.ERRORS;
import static java.util.Objects.nonNull;

@WebServlet(EDITION_ADD_SERVLET)
public class AddEditionServlet extends HttpServlet {

    private EditionDAO editionDAO;
    private EditionService editionService;
    private final Logger logger = Logger.getLogger(AddEditionServlet.class);
    private Map<String, String> errors;

    @Override
    public void init() throws ServletException {
        editionDAO = new EditionDAOImpl();
        editionService = new EditionService();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors = (Map<String, String>) req.getSession().getAttribute(ERRORS);
        if (nonNull(errors)) {
            errors.forEach(req::setAttribute);
        }

        req.getRequestDispatcher(ADD_EDITION_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errors = new HashMap<>();
        try {
            Edition edition = new Edition();
            EditionService editionService = (EditionService) getServletContext().getAttribute(EDITION_SERVICE_CONTEXT);

            editionService.setParametersOfEdition(errors, req, edition);

            if (editionService.getEditions().contains(edition)) {
                errors.put(EDITION, EDITION_ALREADY_EXISTS_ERROR);
            }

            if(errors.isEmpty()){
                logger.debug("Add new edition " + edition);
                editionDAO.add(edition);
                editionService.notifySubscribers(edition.getTheme());
                logger.debug("Edition added successfully");
                resp.sendRedirect(HOME_SERVLET);
                return;
            }

        } catch (DBException e) {
            logger.error("Failed add edition in servlet " + AddEditionServlet.class);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

        req.getSession().setAttribute(ERRORS, errors);
        resp.sendRedirect(EDITION_ADD_SERVLET);

    }

}
