package com.language;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import java.util.*;

@RestController
public class LanguageController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    // 获取当前语言的问候语
    @GetMapping(value = "/greeting", produces = "application/json;charset=UTF-8")
    public Map<String, String> getGreeting() {
        Locale locale = LocaleContextHolder.getLocale();
        Map<String, String> greetings = new HashMap<>();

        // 获取 ResourceBundle
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        // 遍历所有的键
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = bundle.getString(key);
            greetings.put(key, value);
        }

        // 返回所有键值对
        return greetings;
    }

    // 切换语言
    @GetMapping("/setLang")
    public String changeLanguage(@RequestParam("lang") String lang, HttpServletRequest request, HttpServletResponse response) {
        Locale locale = new Locale(lang);
        localeResolver.setLocale(request, response, locale);  // 设置语言
        return "Language switched to " + lang;
    }
}