package otus.homework.deft;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import otus.homework.deft.service.AnswerAnalyzer;
import otus.homework.deft.service.AnswerService;

/**
 * Created by Sergey Golitsyn (deft) on 27.06.2018
 */
@ComponentScan
@Configuration
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class);
        AnswerAnalyzer answerAnalyzer = context.getBean(AnswerService.class);
        answerAnalyzer.analyzeAnswer();
    }

    @Bean
    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("/src/main/resources/bundle");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }


}
