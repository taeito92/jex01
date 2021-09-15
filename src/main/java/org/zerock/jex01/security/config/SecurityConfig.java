package org.zerock.jex01.security.config;

import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.zerock.jex01.security.handler.CustomLoginSuccessHandler;
import org.zerock.jex01.security.service.CustomUserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity //Security 접목을 위해 사용 -> springSecurityFilterChain 자동 연결
@Log4j2
@MapperScan(basePackages = "org.zerock.jex01.security.mapper")
@ComponentScan(basePackages = "org.zerock.jex01.security.service")
//요즘에는 어노테이션으로 하지만 옛날 방식(상속)으로 진행
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  /*  { //default 블럭으로 기본으로 실행할 블럭
        log.info("Security Config....................");
        log.info("Security Config....................");
        log.info("Security Config....................");
        log.info("Security Config....................");
        log.info("Security Config....................");
    }*/

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //BCrypt로 코드 암호화
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/sample/doAll").permitAll() //doAll에 권한 부여
                .antMatchers("/sample/doMember").access("hasRole('ROLE_MEMBER')") //권한에  'ROLE_'
                .antMatchers("/sample/doAdmin").access("hasRole('ROLE_ADMIN')");

        //Login 페이지 호출(custom하지 않으면 자동 생성 -> spring security가 만들어줌)
        http.formLogin().loginPage("/customLogin")
                .loginProcessingUrl("/login")
                .successHandler(customLoginSuccessHandler());
                 //post처리는 spring Security에서 처리하게 만드는 코드

        //http.logout().invalidateHttpSession(true); //invaild가 default 이므로 logout은 동작함.

        http.csrf().disable(); //disable 를 사용하면 csrf 토큰을 사용하지 않으므로 사용자가 url에서 logout이 가능해짐
        http.rememberMe().tokenRepository(persistentTokenRepository())
                .key("zerock").tokenValiditySeconds(60*60*24*30); //한달 동안 로그인 유지

    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUserDetailsService);
        //auth.userDetailsService(customUserDetailsService()); //customUserDetailsService를 통해서 login을 진행한다는 의미
      /*
        auth.inMemoryAuthentication().withUser("member1")
                .password("$2a$10$1IvK53mf1DeGkb0G7vKBBedkrGrg/pqqLoN4/cgOH/.reLyvDgLvu") //member1을 암호화한 코드
                .roles("MEMBER"); //여기서 권한을 부여할 때는 ROLE_을 따로 안붙여도됨.

        auth.inMemoryAuthentication().withUser("admin1")
                .password("$2a$10$1IvK53mf1DeGkb0G7vKBBedkrGrg/pqqLoN4/cgOH/.reLyvDgLvu")
                .roles("MEMBER", "ADMIN"); //여기서 권한을 부여할 때는 ROLE_을 따로 안붙여도됨.
        */
    }

    /*
    @Bean //CustomUserDetailsService에 RequiredArgsConstructor를 달아줌으로서 new로 생성이 불가해짐
    public CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();

    }
    */

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);

        return repository;
    }

}
