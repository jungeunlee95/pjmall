package com.example.pjmall.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.pjmall.backend.config.app.AppSecurityConfig;
import com.example.pjmall.backend.config.app.OAuth2ServerConfig;

@Configuration
@Import({AppSecurityConfig.class, OAuth2ServerConfig.class})
public class AppConfig {
}