package com.masami;

import com.masami.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Date:2019/8/20
 * Author:gyc
 * Desc:
 */
public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        applicationContext.start();

    }
}
