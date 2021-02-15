package com.vvicee.servlet.setting;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static com.vvicee.constant.navigation.Path.HOME_SERVLET;
import static com.vvicee.constant.servlet.LocaleSettingConstant.*;
import static java.util.Objects.nonNull;

@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LocaleServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter(LANGUAGE);
        Locale loc = null;

        switch (language) {
            case ENGLISH -> {
                loc = new Locale(ENGLISH);
                req.getSession().setAttribute(LOCALE, ENGLISH);
            }
            case RUSSIAN -> {
                loc = new Locale(RUSSIAN);
                req.getSession().setAttribute(LOCALE, ENGLISH);
            }
            default -> logger.error("Unexpected value for language: " + language);
        }
        if(nonNull(loc)){
            Locale.setDefault(loc);
        }
        logger.debug("Set locale " + loc);

        resp.sendRedirect(HOME_SERVLET);
    }

}
