package otus.homework.deft.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Sergey Golitsyn (deft) on 27.06.2018
 */

@Component(value = "basePerson")
public class Person {

    private String firstName;
    private String lastName;

    public Person(@Value("${person.defaultFirstName}") String firstName,
                  @Value("${person.defaultLastName}") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private int correctAnswers;

    public void incrementCorrectAnswers() {
        correctAnswers++;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
}
