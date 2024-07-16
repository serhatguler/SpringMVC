package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



//web.xml yerine bu classi kullanacagiz
// distpatcher servletin taninlanmasi,configuration classlarinin yerini gostermek
//bu iki islemi icin AbstractAnnotationConfigDispatcherServletInitializer kullaniriz.

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //Dispatcher: Servlet WebAppContext-> Controller, Handler Maapping, View resolver
    //            Root WebAppContext-> Db ye erisim:repositories, services


    @Override //DB ye erisim(hibernate/JDBC) icin gerekli config class
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootContexConfig.class
        };
    }

    @Override // Controller, Handler Maapping, View resolver(SpringMVC) ile ilgili config class
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebMvcConfig.class
        };
    }



    //http://localhost:8080/SpringMvc2/....
    @Override//hangi url ile gelen requestleri servlet tarafindan karsilanacak
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }



}
