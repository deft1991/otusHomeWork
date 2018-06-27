package otus.homework.deft;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Created by Sergey Golitsyn (deft) on 27.06.2018
 */

@NoArgsConstructor
@AllArgsConstructor
public class Person {
    // че то забыл что имя что фамилия =)
    private String firstName;

    private String lastName;

    private int correctAnswers;

    void incrementCorrectAnswers() {
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
