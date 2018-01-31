package com.ef;

import com.ef.execute.ParseApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Parser {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        // AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Parser.class.getPackage().getName());
        ParseApplication parseApplication = applicationContext.getBean(ParseApplication.class);
        parseApplication.execute(args);

        // applicationContext.close();
        System.out.println("End program!");
    }

}
