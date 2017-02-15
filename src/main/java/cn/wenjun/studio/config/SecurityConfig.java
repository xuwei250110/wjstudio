package cn.wenjun.studio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  // @Autowired
  // private DataSource dataSource;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().passwordEncoder(new
    // ShaPasswordEncoder(256)).rolePrefix("ROLE_");
    auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // 解决in a frame because it set 'X-Frame-Options' to 'DENY'.问题
    // http.headers().frameOptions().disable();
    // http.authorizeRequests().antMatchers("/manager/**").hasRole("ADMIN").anyRequest().permitAll().and().formLogin().and().httpBasic();
    http.authorizeRequests().antMatchers("/manager/**").hasRole("USER").anyRequest().permitAll().and().formLogin().and().httpBasic();
    // 解决403
    http.csrf().disable();
  }

}
