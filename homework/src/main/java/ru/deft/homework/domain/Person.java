package ru.deft.homework.domain;

/**
 * Created by Sergey Golitsyn (deft) on 27.06.2018
 */

public class Person {

  private String firstName;
  private String lastName;

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
