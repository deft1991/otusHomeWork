package otus.homework.deft.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import otus.homework.deft.domain.Person;

import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Sergey Golitsyn (deft) on 28.06.2018
 */
@Getter
@Setter
@Service
public class AnswerService implements AnswerAnalyzer {
    private final Person currentPerson;
    private final String cvsSplitBy;
    private final QuestionReader questionReader;
    private final MessageSource messageSource;

    @Autowired
    public AnswerService(QuestionReader questionReader,
                         @Qualifier("basePerson") Person currentPerson,
                         @Value("${cvsSplitBy}") String cvsSplitBy,
                         MessageSource messageSource) {
        this.questionReader = questionReader;
        this.currentPerson = currentPerson;
        this.cvsSplitBy = cvsSplitBy;
        this.messageSource = messageSource;
    }

    @Override
    public void analyzeAnswer() {
        Scanner scanner = new Scanner(System.in);
        fillPerson(scanner);
        for (String question : questionReader.readQuestions()) {
            String[] arrValues = question.split(cvsSplitBy);
            // костыли, но пока других идей нет
            System.out.println(messageSource.getMessage("question", new String[]{arrValues[1]}, Locale.ENGLISH));
            someTestLogic(currentPerson, scanner, arrValues, messageSource);
        }
        int luckyPercent = currentPerson.getCorrectAnswers() * 100 / 5;
        System.out.println("Оу, красава! " + currentPerson.getFirstName()
                + " " + currentPerson.getLastName()
                + " твой процент везения = " + luckyPercent);
        System.out.println(messageSource.getMessage("result",
                new String[]{currentPerson.getFirstName(),
                        currentPerson.getLastName(),
                        String.valueOf(luckyPercent)}, Locale.ENGLISH));
    }

    private void fillPerson(Scanner scanner) {
        System.out.println(messageSource.getMessage("helloName", new String[0], Locale.forLanguageTag("ru")));
        String name = scanner.next();
        if (!StringUtils.isEmpty(name) && !name.equalsIgnoreCase("null")) {
            currentPerson.setFirstName(name);
        }
        System.out.println(messageSource.getMessage("lastName", new String[0], Locale.ENGLISH));
        String lastName = scanner.next();
        if (!StringUtils.isEmpty(lastName) && !lastName.equalsIgnoreCase("null")) {
            currentPerson.setLastName(lastName);
        }
    }

    private static void someTestLogic(Person currentPerson, Scanner scanner, String[] arrValues, MessageSource messageSource) {
        if ("Q".equalsIgnoreCase(arrValues[0])) {
            System.out.println(arrValues[2] + " " + arrValues[3]);
            boolean isIncorrectAnswerFormat = true;
            while (isIncorrectAnswerFormat) {
                String answer = scanner.next();
                if (answer.equalsIgnoreCase(arrValues[2].split("-")[0].trim())
                        || answer.equalsIgnoreCase(arrValues[3].split("-")[0].trim())) {
                    isIncorrectAnswerFormat = false;
                    String correctAnswer = arrValues[4];
                    if (answer.equalsIgnoreCase(correctAnswer.trim())) {
                        currentPerson.incrementCorrectAnswers();
                    }
                } else {
                    System.out.println(messageSource.getMessage("uncorrectAnswer", new String[0], Locale.ENGLISH));
                    System.out.println(messageSource.getMessage("tryAgain", new String[0], Locale.ENGLISH));
                }
            }
        }
    }
}
