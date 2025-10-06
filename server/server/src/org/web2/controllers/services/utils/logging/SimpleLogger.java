package org.web2.controllers.services.utils.logging;

import java.io.IOException;
import java.util.logging.*;

public class SimpleLogger {
    private static final Logger logger = Logger.getLogger(SimpleLogger.class.getName());
    private static final Formatter SimpleLoggerFormatter = new SimpleLoggerFormatter();

    public static Logger create(){
        try {
            Handler fileHandler = new FileHandler("/tmp/server.log");

            fileHandler.setFormatter(SimpleLoggerFormatter);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);

            return logger;
        } catch (SecurityException | IOException e) {
            throw new RuntimeException("Can't init logger");
        }
    }
}