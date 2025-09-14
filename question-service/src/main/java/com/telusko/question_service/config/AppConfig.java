package com.telusko.question_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
public class AppConfig {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
