package com.m2i.tp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("web.dev")
@ComponentScan("web.dev.only")
public class WebDevOnlyConfig {

}
