package com.viva.stompredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableMongoRepositories
@EnableRedisRepositories
public class StompRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(StompRedisApplication.class, args);
    }

}
