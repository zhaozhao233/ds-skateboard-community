package com.nf.skateboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(com.nf.skateboard.config.MvcConfig.class)
public class AppConfig {

}
