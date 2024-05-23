package it.intesys.academy.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.intesys.academy.controller.TimeOffController;
import it.intesys.academy.repository.SQLTimeOffRepository;
import it.intesys.academy.repository.TimeOffRepository;
import it.intesys.academy.service.TimeOffService;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@PropertySource("classpath:application.properties")
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

    @Bean
    public TimeOffRepository timeOffRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new SQLTimeOffRepository(namedParameterJdbcTemplate);
    }

    @Bean
    public TimeOffService timeOffService(TimeOffRepository timeOffRepository) {
        return new TimeOffService(timeOffRepository);
    }

    @Bean
    public TimeOffController timeOffController(TimeOffService timeOffService) {
        return new TimeOffController(timeOffService);
    }

}
