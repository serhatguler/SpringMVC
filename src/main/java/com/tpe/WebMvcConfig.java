package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.tpe")//default:com.tpe
@EnableWebMvc// WebMVCyi aktif et
public class WebMvcConfig implements WebMvcConfigurer {

    //View name(hello...) karsilik gelen jsp dosyasinin cozumlenmesini: view resolver
    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class); //Java standartTagLibrary: JSP dosyalari icinde  HTML
        //taglerini kullanarak daha az kod yazmamizi saglar.
        resolver.setPrefix("/WEB_INF/views/");//view dosyalari nerede(dizin)
        resolver.setSuffix(".jsp");//view dosya uzantisi
        return resolver;
    }

    //css,image gibi static olan kaynaklarin webserver tarafindan karsilandigi icin dispatcher servleta
    // gonderilmesine gerek yok.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")//bu pathdeki dosylari statik olarak sun
                .addResourceLocations("/resources")
                .setCachePeriod(0);//cacheleme icin belirli bir sure verilebilir.
    }
}