package it.intesys.academy.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;


@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("it.intesys.academy")
public class AppConfiguration {

    @Bean
    public DataSource dataSource(Environment environment) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("database.url"));
        hikariConfig.setUsername(environment.getProperty("database.user"));
        hikariConfig.setPassword(environment.getProperty("database.password"));
        hikariConfig.setDriverClassName("org.h2.Driver");
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
