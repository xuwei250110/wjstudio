package cn.wenjun.studio.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "cn.wenjun.studio.web.controller" })
public class ServletConfig {

}
