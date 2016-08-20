package com.yangke.configuration;

import com.yangke.interceptor.LoginRequiredIntereptor;
import com.yangke.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by yangke on 16/8/20.
 */
@Component
public class QAWebConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    PassportInterceptor passportInterceptor;
    @Autowired
    LoginRequiredIntereptor loginRequiredIntereptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(loginRequiredIntereptor).addPathPatterns("/user/*");
        super.addInterceptors(registry);
    }
}
