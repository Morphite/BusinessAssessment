package com.diplome.businessassessment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ThymeleafProperties thymeleafProperties;

    @Value("${spring.thymeleaf.templates-root}")
    private String templatesRoot;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public ITemplateResolver defaultTemplateResolver(){
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setSuffix(thymeleafProperties.getSuffix());
        resolver.setPrefix(templatesRoot);
        resolver.setTemplateMode(thymeleafProperties.getMode());
        resolver.setCacheable(thymeleafProperties.isCache());
        return resolver;
    }
}
