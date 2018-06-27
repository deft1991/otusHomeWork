package otus.homework.deft;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Sergey Golitsyn (deft) on 28.06.2018
 */
public class AnswersService implements AnalizeAnswers {
    private BufferedReader bufferedReader;
    private Person currentPerson;
    private String cvsSplitBy;
    private String csvFile;

    public AnswersService(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void analizeAnswer() {
        // не нагуглил как в бин засунуть Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Привет! Как тебя зовут?");
        String name = scanner.next();
        currentPerson.setFirstName(name);
        System.out.println("А какая твоя фамилия?");
        String age = scanner.next();
        currentPerson.setLastName(age);
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] arrValues = line.split(cvsSplitBy);
                // костыли, но пока других идей нет
                System.out.println(arrValues[1]);
                someTestLogic(currentPerson, scanner, arrValues);
            }
            int luckyPercent = currentPerson.getCorrectAnswers() * 100 / 5;
            System.out.println("Оу, красава! " + currentPerson.getFirstName()
                    + " " + currentPerson.getLastName()
                    + " твой процент везения = " + luckyPercent);
        } catch (IOException e) {
            System.out.println("Ой ой что то с csv");
        }
    }

    private static void someTestLogic(Person currentPerson, Scanner scanner, String[] arrValues) {
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
                    System.out.println("Не корректный вариант ответа");
                    System.out.println("Попробуй еще раз");
                }
            }
        }
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    public String getCvsSplitBy() {
        return cvsSplitBy;
    }

    public void setCvsSplitBy(String cvsSplitBy) {
        this.cvsSplitBy = cvsSplitBy;
    }

    public String getCsvFile() {
        return csvFile;
    }

    public void setCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }

}
