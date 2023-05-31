package golovin.store.gusli.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum LogFactory {
    console("console"),
    application("application"),
    api("api");

    final Logger logger;

    LogFactory(String name) {
        logger = LoggerFactory.getLogger(name);
    }

    public Log get(String tag) {
        return new Log(tag, logger);
    }

    public Log get(Class<?> clazz) {
        return get(clazz.getSimpleName());
    }
}
