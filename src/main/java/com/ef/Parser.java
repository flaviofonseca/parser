package com.ef;

import com.ef.execute.ParseApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Parser.class.getPackage().getName());
        ParseApplication parseApplication = applicationContext.getBean(ParseApplication.class);
        parseApplication.execute(args);

        applicationContext.close();
        LOGGER.info("End program!");
    }

}
