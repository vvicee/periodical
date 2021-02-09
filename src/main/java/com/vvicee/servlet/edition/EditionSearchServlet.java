package com.vvicee.servlet.edition;

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
import static com.vvicee.constant.servlet.EditionServletConstant.SEARCH_PARAMETER;

@WebServlet(SEARCH_EDITION_SERVLET)
public class EditionSearchServlet extends HttpServlet {

    Logger logger = Logger.getLogger(EditionSearchServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditionService editionService = (EditionService) getServletContext().getAttribute(EDITION_SERVICE_CONTEXT);

        editionService.resetFilters();
        String parameter;
        if (!(parameter = req.getParameter(SEARCH_PARAMETER)).isEmpty()) {
            editionService.filterEditions(
                    editionService.getPredicateForTitle(parameter)
                            .or(editionService.getPredicateForPublisher(parameter))
            );
        }
        logger.debug("Editions found successfully");

        resp.sendRedirect(HOME_SERVLET);
    }
}
