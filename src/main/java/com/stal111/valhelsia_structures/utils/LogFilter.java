package com.stal111.valhelsia_structures.utils;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

import org.apache.logging.log4j.core.Filter.Result;

/**
 * Log Filter
 * Valhelsia Structures  - com.stal111.valhelsia_structures.utils.LogFilter
 *
 * @author Valhelsia Team
 * @version 2.0.0
 * @since 2021-07-11
 */
@Plugin(name = "LogFilter", category = Node.CATEGORY, elementType = Filter.ELEMENT_TYPE)
public class LogFilter extends AbstractFilter {

    @Override
    public Filter.Result filter(LogEvent event) {
        Message message = event.getMessage();
        if (message != null) {
            if (message.getFormattedMessage().contains("Unknown structure start: valhelsia_structures:")) {
                return Result.DENY;
            }
        }

        return Result.NEUTRAL;
    }
}
