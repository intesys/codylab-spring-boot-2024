package it.intesys.academy.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.intesys.academy.controller.TimeOffController;
import it.intesys.academy.repository.SQLTimeOffRepository;
import it.intesys.academy.repository.TimeOffRepository;
import it.intesys.academy.service.TimeOffService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfiguration {

    public static Properties appProperties;

    @Bean
    public Properties appProperties() {
        if (appProperties == null) {
            Properties prop = new Properties();
            try (InputStream input = AppConfiguration.class.getClassLoader().getResourceAsStream("application.properties")) {
                prop.load(input);
            } catch (IOException ex) {
                throw new IllegalStateException("Property load fail", ex);
            }
            appProperties = prop;
        }

        return appProperties;
    }

    @Bean
    private DataSource dataSource(@Qualifier("appProperties") Properties appProperties) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(appProperties.getProperty("database.url"));
        hikariConfig.setUsername(appProperties.getProperty("database.user"));
        hikariConfig.setPassword(appProperties.getProperty("database.password"));
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
