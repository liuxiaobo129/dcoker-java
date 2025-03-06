package com.language;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

@Configuration
public class LocalizationConfig {

    // 配置 LocaleResolver
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH);  // 默认语言为英语
        resolver.setCookieName("lang");  // 设置语言cookie的名称
        resolver.setCookieMaxAge(3600);  // 设置cookie的最大生命周期（秒）
        return resolver;
    }

    // 配置 MessageSource
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");  // 配置消息资源文件的位置
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}