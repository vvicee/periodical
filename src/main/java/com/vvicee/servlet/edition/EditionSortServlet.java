package com.vvicee.servlet.edition;

import com.vvicee.entity.edition.Edition;
import com.vvicee.service.EditionService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static com.vvicee.constant.context.ContextConstant.EDITION_SERVICE_CONTEXT;
import static com.vvicee.constant.navigation.Path.*;
import static com.vvicee.constant.servlet.EditionServletConstant.*;

@WebServlet(SORT_EDITION_SERVLET)
public class EditionSortServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EditionSortServlet.class);
    Map<String, Comparator<Edition>> comparatorMap;

    @Override
    public void init() throws ServletException {
        comparatorMap = new HashMap<>();
        comparatorMap.put(COMPARATOR_TITLE_FROM_A_Z, Comparator.comparing(Edition::getTitle));
        comparatorMap.put(COMPARATOR_TITLE_FROM_Z_A, Comparator.comparing(Edition::getTitle).reversed());
        comparatorMap.put(COMPARATOR_PRICE_DESC, Comparator.comparing(Edition::getPrice).reversed());
        comparatorMap.put(COMPARATOR_PRICE_ASC, Comparator.comparing(Edition::getPrice));
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EditionService editionService = (EditionService) getServletContext().getAttribute(EDITION_SERVICE_CONTEXT);

        String comparedParameter;
        if ((comparedParameter = req.getParameter(COMPARED_PARAMETER)) != null) {
            editionService.sortCurrentEditions(comparatorMap.get(comparedParameter));
        }
        logger.debug("Edition sorted successfully");
        resp.sendRedirect(HOME_SERVLET);
    }

}
