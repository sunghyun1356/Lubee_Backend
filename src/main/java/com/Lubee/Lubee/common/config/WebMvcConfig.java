package com.Lubee.Lubee.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    private static final String[] ALLOWED_ORIGINS = {
            "http://localhost:5173",
            "http://localhost:8080",
            "https://lubee.shop"
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedOrigins(ALLOWED_ORIGINS)
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedMethods("OPTIONS","GET","POST","PUT","DELETE");
    }
}
