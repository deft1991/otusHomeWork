package ru.deft.homework.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.deft.homework.configuration.BaseConfiguration;
import ru.deft.homework.domain.Person;

import java.util.Locale;

/**
 * Created by Sergey Golitsyn (deft) on 28.06.2018
 */
@Getter
@Setter
@Service
public class AnswerService implements AnswerAnalyzer {
  private final BaseConfiguration baseConfiguration;
  private final Person currentPerson;
  private final QuestionReader questionReader;
  private final MessageSource messageSource;
  private final ConsoleService consoleService;
  private static final String IS_QUESTION = "Q";
  private static final String REGEX_SPLITTER = "-";

  @Autowired
  public AnswerService(QuestionReader questionReader,
					   @Qualifier("basePerson") Person currentPerson,
					   BaseConfiguration baseConfiguration,
					   ConsoleService consoleService,
					   MessageSource messageSource) {
	this.questionReader = questionReader;
	this.currentPerson = currentPerson;
	this.baseConfiguration = baseConfiguration;
	this.consoleService = consoleService;
	this.messageSource = messageSource;
  }

  @Override
  public void analyzeAnswer() {
	fillPerson();
	for (String question : questionReader.readQuestions()) {
	  String[] arrValues = question.split(baseConfiguration.getSplitter());
	  // костыли, но пока других идей нет
	  System.out.println(messageSource.getMessage("question", new String[]{arrValues[1]}, Locale.ENGLISH));
	  someTestLogic(currentPerson, consoleService, arrValues, messageSource);
	}
	int luckyPercent = currentPerson.getCorrectAnswers() * 100 / 5;
	System.out.println(messageSource.getMessage("result",
			new String[]{currentPerson.getFirstName(),
					currentPerson.getLastName(),
					String.valueOf(luckyPercent)}, Locale.ENGLISH));
  }

  private void fillPerson() {
	System.out.println(messageSource.getMessage("helloName", new String[0], Locale.ENGLISH));
	String name = consoleService.read();
	if (!StringUtils.isEmpty(name) && !name.equalsIgnoreCase("null")) {
	  currentPerson.setFirstName(name);
	}
	System.out.println(messageSource.getMessage("lastName", new String[0], Locale.ENGLISH));
	String lastName = consoleService.read();
	if (!StringUtils.isEmpty(lastName) && !lastName.equalsIgnoreCase("null")) {
	  currentPerson.setLastName(lastName);
	}
  }

  @SuppressWarnings("Duplicates")
  private static void someTestLogic(Person currentPerson, ConsoleService consoleService, String[] arrValues, MessageSource messageSource) {
	if (IS_QUESTION.equalsIgnoreCase(arrValues[0])) {
	  System.out.println(arrValues[2] + " " + arrValues[3]);
	  boolean isIncorrectAnswerFormat = true;
	  while (isIncorrectAnswerFormat) {
		String answer = consoleService.read();
		if (answer.equalsIgnoreCase(arrValues[2].split(REGEX_SPLITTER)[0].trim())
				|| answer.equalsIgnoreCase(arrValues[3].split(REGEX_SPLITTER)[0].trim())) {
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
