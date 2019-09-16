package ru.topjava.graduation.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContextTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        for (String currentBean : context.getBeanDefinitionNames()) {
            System.out.println(currentBean);
        }
    }
}
