package com.eresko.listener;

import com.eresko.exception.DBException;
import com.eresko.service.EditionService;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static com.eresko.constant.context.ContextConstant.EDITION_SERVICE_CONTEXT;

@WebListener
public class ContextListenerServlet implements ServletContextListener {

    private final Logger logger = Logger.getLogger(ContextListenerServlet.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        initEditionService(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    private void initEditionService(ServletContext servletContext) {
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
