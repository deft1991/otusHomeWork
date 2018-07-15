package ru.deft.homework.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * Created by Sergey Golitsyn (deft) on 15.07.2018
 */
@Service
public class ConsoleServiceImpl implements ConsoleService{
  @Override
  public String read() {
	Scanner scanner = new Scanner(System.in);
	return scanner.next();
  }

  @Override
  public String write(String s) {
	System.out.println(s);
	return s;
  }
}
