package otus.homework.deft.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Sergey Golitsyn (deft) on 01.07.2018
 */
@Configuration
@PropertySource("/application.properties")
public class BaseConfig {
    @Value("${file.csvFile}")
    String csvFile;

    @Bean
    FileReader fileReader() throws FileNotFoundException {
        return new FileReader(csvFile);
    }

    @Bean
    BufferedReader bufferedReader(FileReader fileReader) {
        return new BufferedReader(fileReader);
    }
}
