package com.concertreservation.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.concertreservation.domain.repository")
public class DataSourceConfig {
    // 추가 설정이 필요한 경우 여기에 구성할 수 있습니다.
}
