package com.person.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig2 implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File file1 = new File("");
        try {
            String filePath = file1.getCanonicalPath()+ File.separator +"src"+ File.separator +"main"+
                    File.separator +"resources"+File.separator+"static"+File.separator+"upload"+File.separator;
            registry.addResourceHandler("/upload/**").addResourceLocations("file:"+filePath);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
