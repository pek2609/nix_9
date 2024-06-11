package ua.com.alevel.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//TODO find problems
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/external-files/images/");

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/"); // 'images/' is the directory path
    }
}
