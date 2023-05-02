package com.etiya.ecommerceDemo;

import com.etiya.ecommerceDemo.business.constants.Messages;
import com.etiya.ecommerceDemo.core.exceptions.BusinessException;
import com.etiya.ecommerceDemo.core.exceptions.NotFoundException;
import com.etiya.ecommerceDemo.core.utils.result.ErrorDataResult;
import com.etiya.ecommerceDemo.core.utils.result.ErrorResult;
import com.etiya.ecommerceDemo.core.utils.result.Result;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
@RestControllerAdvice
public class EcommerceDemoApplication {

    @Autowired
    private MessageSource messageSource;

    public static void main(String[] args) {
        SpringApplication.run(EcommerceDemoApplication.class, args);
    }

    @Bean
    public ResourceBundleMessageSource bundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
        acceptHeaderLocaleResolver.setDefaultLocale(Locale.US);
        return acceptHeaderLocaleResolver;
    }

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleBusinessException(BusinessException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ErrorDataResult<>(errors, messageSource.getMessage(Messages.Validation.validationError, null, LocaleContextHolder.getLocale()));
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleNotFoundException(NotFoundException exception) {
        return new ErrorResult(exception.getMessage());
    }


}

// TO-DO update değişmeyen fieldlar için verilen zaten mevcut hatasını düzelt.