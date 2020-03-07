package com.getsong.mockito.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigServer
public class AnnotationApplication {

  @Autowired GinService ginService;

  public static void main(String[] args) {
    SpringApplication.run(AnnotationApplication.class, args);
  }

  @Bean
  ApplicationRunner runner() {
    return args -> ginService.pourGin();
  }
}
