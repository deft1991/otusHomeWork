package ru.deft.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.deft.homework.configuration.BaseConfiguration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sergey Golitsyn (deft) on 01.07.2018
 */
@Service
public class QuestionReaderService implements QuestionReader {
    private BaseConfiguration baseConfiguration;
    private MessageSource messageSource;

    @Autowired
    public QuestionReaderService(BaseConfiguration baseConfiguration, MessageSource messageSource) {
        this.baseConfiguration = baseConfiguration;
        this.messageSource = messageSource;
    }

    @Override
    public List<String> readQuestions() {
        List<String> questions = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(baseConfiguration.getCsv()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                questions.add(line);
            }
        } catch (IOException e) {
            System.out.println(messageSource.getMessage("errCsvRead", new String[0], Locale.ENGLISH));
        }
        return questions;
    }
}
