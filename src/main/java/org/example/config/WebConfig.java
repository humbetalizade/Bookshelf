package org.example.config;

import org.example.config.StringToGenreConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringToGenreConverter stringToGenreConverter;

    @Autowired
    public WebConfig(StringToGenreConverter stringToGenreConverter) {
        this.stringToGenreConverter = stringToGenreConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToGenreConverter);
    }
}
