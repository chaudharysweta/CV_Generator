package com.example.cv_generator.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class CustomMessageSource {

    private final MessageSource messageSource;

  public String get(String code){
      return messageSource.getMessage(code,null,getCurrentLocale());
  }

    private Locale getCurrentLocale() {
      Locale locale= LocaleContextHolder.getLocale();
      if (locale.getDisplayLanguage().equalsIgnoreCase("np")){
          locale=new Locale("np","Nepal");
      }
      return locale;
    }
}
