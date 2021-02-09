package com.eresko.servlet.edition;

import com.eresko.service.EditionService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.eresko.constant.context.ContextConstant.EDITION_SERVICE_CONTEXT;
import static com.eresko.constant.entity.EditionConstant.EDITION_CATEGORY;
import static com.eresko.constant.entity.EditionConstant.EDITION_THEME;
import static com.eresko.constant.navigation.Path.*;


@WebServlet(FILTER_EDITION_SERVLET)
public class EditionFilterServlet extends HttpServlet {
    public static final Logger logger = Logger.getLogger(EditionFilterServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EditionService editionService = (EditionService) getServletContext().getAttribute(EDITION_SERVICE_CONTEXT);

        String[] categories = req.getParameterValues(EDITION_CATEGORY);
        String[] themes = req.getParameterValues(EDITION_THEME);

        editionService.filterEditions(
                editionService.getPredicateForCategories(categories)
                        .and(editionService.getPredicateForThemes(themes)));

        logger.debug("Editions successfully filtered");

        resp.sendRedirect(HOME_SERVLET);
    }
}
