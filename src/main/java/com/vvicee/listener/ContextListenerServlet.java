package com.vvicee.listener;

import com.vvicee.exception.DBException;
import com.vvicee.service.EditionService;
import com.vvicee.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static com.vvicee.constant.context.ContextConstant.EDITION_SERVICE_CONTEXT;
import static com.vvicee.constant.context.ContextConstant.USER_SERVICE_CONTEXT;

@WebListener
public class ContextListenerServlet implements ServletContextListener {

    private final Logger logger = Logger.getLogger(ContextListenerServlet.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        initEditionService(servletContext);
        initUserService(servletContext);
    }



    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    public void initUserService(ServletContext servletContext) {
        try {
            UserService userService = new UserService();
            servletContext.setAttribute(USER_SERVICE_CONTEXT, userService);
        } catch (DBException exception) {
            logger.error("Failed database");
            throw new IllegalStateException();
        }
        logger.debug("UserService initialize successfully");
    }
    public void initEditionService(ServletContext servletContext) {
        try {
            EditionService editionService = new EditionService();
            editionService.loadEditionsFromDB();
            servletContext.setAttribute(EDITION_SERVICE_CONTEXT, editionService);
        } catch (DBException exception) {
            logger.error("Failed database");
            throw new IllegalStateException();
        }
        logger.debug("EditionService initialize successfully");
    }
}
