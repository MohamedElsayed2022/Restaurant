//package com.coding.resturant.config;
//
//import com.coding.resturant.model.Category;
//import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
//import org.springframework.http.HttpMethod;
//
//@Configuration
//public class DataRestApiConfig implements RepositoryRestConfigurer {
//    @Override
//    public void configReposioryRestConfiguration(RepositoryRestConfigurer config){
//     HttpMethod[] preventMethod = {HttpMethod.GET , HttpMethod.POST , HttpMethod.PUT , HttpMethod.DELETE };
//      disableHttpMethod(Category.class , config , preventMethod);
//
//    }
//
//    private void disableHttpMethod(Class<Category> categoryClass, RepositoryRestConfigurer config, HttpMethod[] preventMethod) {
//     config.getExposureConfiguration()
//    }
//}
