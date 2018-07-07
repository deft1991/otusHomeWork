package ru.deft.homework.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.deft.homework.domain.Person;

/**
 * Created by Sergey Golitsyn (deft) on 01.07.2018
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("application")
@EnableConfigurationProperties
public class BaseConfiguration {

    private String splitter;
    private String csv;
    private String defaultFirstName;
    private String defaultLastName;



    @Bean
	@Qualifier("basePerson")
    public Person defaultPerson(){
      return new Person(){{
        setFirstName(defaultFirstName);
        setLastName(defaultLastName);
	  }};
	}
}
