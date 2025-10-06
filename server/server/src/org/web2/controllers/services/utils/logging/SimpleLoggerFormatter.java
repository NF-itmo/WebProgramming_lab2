package org.web2.controllers.services.utils.logging;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SimpleLoggerFormatter extends Formatter {
    private final String logstringFormat = "%s - %s %s \t %s \n";

    @Override
    public String format(LogRecord record) {
        return String.format(
                logstringFormat,

                new Date(record.getMillis()),
                record.getSourceClassName(),
                record.getSourceMethodName(),
                record.getMessage()
        );
    }

}