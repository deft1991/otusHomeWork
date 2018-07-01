package otus.homework.deft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sergey Golitsyn (deft) on 01.07.2018
 */
@Service
public class QuestionReaderService implements QuestionReader {
    private final BufferedReader bufferedReader;
    private final MessageSource messageSource;

    @Autowired
    public QuestionReaderService(BufferedReader bufferedReader,
                                 MessageSource messageSource) {
        this.bufferedReader = bufferedReader;
        this.messageSource = messageSource;
    }

    @Override
    public List<String> readQuestions() {
        List<String> questions = new ArrayList<>();
        try {
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
