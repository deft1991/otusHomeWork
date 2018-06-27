package otus.homework.deft;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Sergey Golitsyn (deft) on 27.06.2018
 */
public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("context.xml");
        AnalizeAnswers analizeAnswers = (AnalizeAnswers) context.getBean("answersService");
        analizeAnswers.analizeAnswer();
    }


}
