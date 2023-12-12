package com.example.treasuregame_back;

import com.example.treasuregame_back.View.LoginView;
import com.example.treasuregame_back.game.GameController;
import com.example.treasuregame_back.game.GameRepository;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.spring.VaadinApplicationConfiguration;
import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter.getDefaultWebSecurityIgnoreMatcher;

@SpringBootApplication
@Theme(themeClass = Lumo.class, variant = Lumo.DARK)
public class TreasuregameBackApplication extends VaadinWebSecurityConfigurerAdapter implements AppShellConfigurator {
    public static void main(String[] args) {
        SpringApplication.run(TreasuregameBackApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);
        getDefaultWebSecurityIgnoreMatcher("http://localhost:8080/api/v1/game");
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**");
    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password("{noop}admin")
                        .roles("ADMIN")
                        .build()
        );
    }
}
