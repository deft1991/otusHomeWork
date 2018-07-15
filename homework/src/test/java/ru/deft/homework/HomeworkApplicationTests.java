package ru.deft.homework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.deft.homework.service.AnswerService;
import ru.deft.homework.service.ConsoleService;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeworkApplicationTests {

    @Autowired
    AnswerService answerService;

    @MockBean
    ConsoleService consoleService;

    @Test
    public void analyzeAnswerTest() {
        given(this.consoleService.read()).willReturn("a");
        assertEquals(consoleService.read(),"a");
        answerService.analyzeAnswer();
    }

}
