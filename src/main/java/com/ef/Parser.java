package com.ef;

import com.ef.execute.ParseApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Parser {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Parser.class.getPackage().getName());
        ParseApplication parseApplication = applicationContext.getBean(ParseApplication.class);
        parseApplication.execute(args);

        applicationContext.close();
        System.out.println("Fim!");
    }

}
