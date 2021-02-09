package com.eresko.servlet;

import com.eresko.service.EditionService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.eresko.constant.context.ContextConstant.EDITION_SERVICE_CONTEXT;
import static com.eresko.constant.navigation.Path.HOME_PAGE;
import static com.eresko.constant.navigation.Path.HOME_SERVLET;
import static com.eresko.constant.servlet.EditionServletConstant.LIST_OF_EDITIONS;

@WebServlet(HOME_SERVLET)
public class HomeServlet extends HttpServlet {

    EditionService editionService;
    private final Logger logger = Logger.getLogger(HomeServlet.class);

    @Override
    public void init() throws ServletException {
        editionService = (EditionService) getServletContext().getAttribute(EDITION_SERVICE_CONTEXT);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(LIST_OF_EDITIONS, editionService.getCurrentEditions());
        req.getRequestDispatcher(HOME_PAGE).forward(req, resp);
    }

}
