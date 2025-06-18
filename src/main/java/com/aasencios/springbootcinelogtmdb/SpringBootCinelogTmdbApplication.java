package com.aasencios.springbootcinelogtmdb;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCinelogTmdbApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("DB_USER"));
        System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));
        System.setProperty("tmdb.api.key", dotenv.get("TMDB_API_KEY"));

        SpringApplication.run(SpringBootCinelogTmdbApplication.class, args);
    }

}
