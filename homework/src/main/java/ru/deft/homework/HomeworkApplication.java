package ru.deft.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.deft.homework.service.AnswerAnalyzer;

@EnableAsync
@SpringBootApplication
public class HomeworkApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HomeworkApplication.class);
        AnswerAnalyzer answerAnalyzer = context.getBean(AnswerAnalyzer.class);
        answerAnalyzer.analyzeAnswer();
        System.exit(0);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/bundle");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
