package ru.deft.homework.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.deft.homework.domain.Person;

/**
 * Created by Sergey Golitsyn (deft) on 01.07.2018
 */
@Configuration
@ConfigurationProperties("application")
@EnableConfigurationProperties
public class BaseConfiguration {

    private String splitter;
    private String csv;
    private String defaultFirstName;
    private String defaultLastName;

    public String getSplitter() {
        return splitter;
    }

    public void setSplitter(String splitter) {
        this.splitter = splitter;
    }

    public String getCsv() {
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }

    public String getDefaultFirstName() {
        return defaultFirstName;
    }

    public void setDefaultFirstName(String defaultFirstName) {
        this.defaultFirstName = defaultFirstName;
    }

    public String getDefaultLastName() {
        return defaultLastName;
    }

    public void setDefaultLastName(String defaultLastName) {
        this.defaultLastName = defaultLastName;
    }

    @Bean
    @Qualifier("basePerson")
    public Person defaultPerson() {
        return new Person() {{
            setFirstName(defaultFirstName);
            setLastName(defaultLastName);
        }};
    }
}
