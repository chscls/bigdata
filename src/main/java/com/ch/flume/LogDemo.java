package com.ch.flume;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class LogDemo {
    static final Log log = LogFactory.getLog(LogDemo.class);

    public static void main(String[] args) {
        log.info("{'name':'Adam', 'age':'26', 'skill':'reading'}");
    }
}
