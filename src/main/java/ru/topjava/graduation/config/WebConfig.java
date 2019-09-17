package ru.topjava.graduation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("ru.topjava.graduation.web")
public class WebConfig implements WebMvcConfigurer {
    //TODO ПРОГУГЛИТЬ И ДОПИСАТЬ ВЕБ КОНФИГ
}
