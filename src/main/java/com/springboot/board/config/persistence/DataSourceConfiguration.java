package com.springboot.board.config.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.InvalidPropertiesFormatException;

@EnableJpaAuditing
@Configuration
@RequiredArgsConstructor
public class DataSourceConfiguration {
    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private final static String PACKAGE = "com.springboot.board";

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean boardEntityManager(
            DbProperties dbProperties, @Qualifier("boardDataSource") DataSource dataSource
    ) {
        EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(), dbProperties.getJpaProperties().getProperties(),
                null
        );

        return builder
                .dataSource(dataSource)
                .properties(dbProperties.getJpaProperties().getProperties())
                .persistenceUnit("boardEntityManager")
                .packages(PACKAGE)
                .build();
    }

    @Bean
    public DataSource boardDataSource(DbProperties dbProperties, JpaProperties jpaProperties) throws InvalidPropertiesFormatException {
        dbProperties = getValidDbProperties(dbProperties, jpaProperties);

        return DataSourceBuilder
                .create()
                .url(dbProperties.getConnectionString())
                .username(dbProperties.getUsername())
                .password(dbProperties.getPassword())
                .driverClassName(DRIVER_NAME)
                .build();
    }

    public DbProperties getValidDbProperties( DbProperties dbProperties, JpaProperties jpaProperties) throws InvalidPropertiesFormatException {
        dbProperties.setJpaProperties(jpaProperties);

        if (dbProperties.isValid()) {
            return dbProperties;
        }

        dbProperties.setHostname("");
        dbProperties.setUsername("");
        dbProperties.setPassword("");

        return dbProperties;
    }

}
