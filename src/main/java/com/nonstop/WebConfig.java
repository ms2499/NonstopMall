package com.nonstop;

import com.nonstop.Interceptor.ManLoginInterceptor;
import com.nonstop.Interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    ManLoginInterceptor manLoginInterceptor;
    @Autowired
    UserLoginInterceptor userLoginInterceptor;

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        //全部允許，包含跨來源要求
//        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
//    }
    //因為Interceptor順序問題，會先執行自訂Interceptor最後才執行Cors攔截器，導致若自訂Interceptor不通過就會出現跨網域要求被禁止
    //改用Filter設定cors，Filter順序比Interceptor優先
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //在這個patterns裡面就是不用登入的
        List<String> patterns = new ArrayList<>();
        patterns.add("/manager/Login");

        registry.addInterceptor(manLoginInterceptor)
                .addPathPatterns("/manager/**")
                .addPathPatterns("/back/**")
                .excludePathPatterns(patterns);

        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns("/order/**");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        for(HttpMessageConverter<?> converter : converters){
            if (converter instanceof StringHttpMessageConverter){
                ((StringHttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8);
            }
            if (converter instanceof MappingJackson2HttpMessageConverter){
                ((MappingJackson2HttpMessageConverter) converter).setDefaultCharset((StandardCharsets.UTF_8));
            }
        }
    }
}