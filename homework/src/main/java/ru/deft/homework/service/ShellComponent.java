package ru.deft.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author Golitsyn Sergey (sgolitsyn)
 * @since 7/13/2018
 */
@org.springframework.shell.standard.ShellComponent
public class ShellComponent {

  private final AnswerAnalyzer answerAnalyzer;

  @Autowired
  public ShellComponent(AnswerAnalyzer answerAnalyzer) {
	this.answerAnalyzer = answerAnalyzer;
  }

  @ShellMethod("Quiz")
  public void quiz() {
	answerAnalyzer.analyzeAnswer();
  }
}
