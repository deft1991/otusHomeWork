package ru.deft.homework.configuration;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Sergey Golitsyn (deft) on 07.07.2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseConfigurationTest {

  @Autowired
  private BaseConfiguration baseConfiguration;

  @Test
  public void testGetSplit() {
	assertEquals("\\,", baseConfiguration.getSplitter());
  }

  @Test
  public void testGetCsv() {
	assertEquals("src/main/resources/questions.csv", baseConfiguration.getCsv());
  }

  @Test
  public void testGetDefaultFirstName() {
	assertEquals("DefaultFirstName", baseConfiguration.getDefaultFirstName());
  }

  @Test
  public void testGetDefaultLastName() {
	assertEquals("DefaultLastName", baseConfiguration.getDefaultLastName());
  }

}