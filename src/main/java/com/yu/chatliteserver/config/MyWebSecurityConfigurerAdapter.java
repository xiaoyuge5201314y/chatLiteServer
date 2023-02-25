package com.yu.chatliteserver.config;//package com.wu.blog.config;
//
//import com.wu.blog.auth.MyUserDetailsService;
//import com.wu.blog.auth.MyUsernamePasswordAuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//
//
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin().loginProcessingUrl("/login");
//        http.csrf().disable().exceptionHandling();
//        http.addFilterAfter(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        super.configure(http);
//    }
//
//    @Bean
//    public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
//        MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new MyUsernamePasswordAuthenticationFilter();
//        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
//        return myUsernamePasswordAuthenticationFilter;
//    }
//
//    @Override
//    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(myUserDetailsService);
//
//    }
//}
