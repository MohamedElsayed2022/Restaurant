package com.coding.resturant.springsecurity.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration // هذه الأنوستيشن تخبر سبرينج بوت بأن يقرأ هذا الكلاس عند تشغيل المشروع
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // الحصول على المسار الكامل لمجلد الـ uploads على جهازك
        Path uploadDir = Paths.get("./uploads");
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        // ربط رابط الـ URL بالمجلد الفعلي على الهارد ديسك
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadPath + "/");
    }
}