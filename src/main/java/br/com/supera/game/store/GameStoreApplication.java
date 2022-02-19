package br.com.supera.game.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class GameStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(GameStoreApplication.class, args);
    }
}
