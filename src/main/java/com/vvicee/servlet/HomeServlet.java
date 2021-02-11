package com.vvicee.servlet;

import com.vvicee.service.EditionService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.vvicee.constant.context.ContextConstant.EDITION_SERVICE_CONTEXT;
import static com.vvicee.constant.navigation.Path.HOME_PAGE;
import static com.vvicee.constant.navigation.Path.HOME_SERVLET;
import static com.vvicee.constant.servlet.EditionServletConstant.LIST_OF_EDITIONS;
import static com.vvicee.constant.servlet.EditionServletConstant.SAVE_ACTION;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@WebServlet(HOME_SERVLET)
public class HomeServlet extends HttpServlet {

    EditionService editionService;
    private final Logger logger = Logger.getLogger(HomeServlet.class);

    @Override
    public void init() throws ServletException {
        editionService = (EditionService) getServletContext().getAttribute(EDITION_SERVICE_CONTEXT);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute(LIST_OF_EDITIONS, editionService.getCurrentEditions());
        req.getRequestDispatcher(HOME_PAGE).forward(req, resp);
    }


}
