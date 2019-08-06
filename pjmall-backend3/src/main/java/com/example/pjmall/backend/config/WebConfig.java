package com.example.pjmall.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.example.pjmall.backend.config.web.MVCConfig;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.example.pjmall.backend.controller", "com.example.pjmall.backend.exception"})
@Import({ MVCConfig.class })
public class WebConfig {
}
