package com.radovan.play.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.inject.Singleton;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

@Singleton
public class HibernateUtil {

    private final SessionFactory sessionFactory;


    public HibernateUtil() {
        this.sessionFactory = createSessionFactory();
    }

    private SessionFactory createSessionFactory() {
        try {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/image-db");
            hikariConfig.setUsername("postgres");
            hikariConfig.setPassword("1111"); // use your own password
            hikariConfig.setDriverClassName("org.postgresql.Driver");
            hikariConfig.setMinimumIdle(2);
            hikariConfig.setIdleTimeout(600000);
            hikariConfig.setMaximumPoolSize(10);
            hikariConfig.setConnectionTimeout(30000);
            hikariConfig.setMaxLifetime(1800000);

            HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySetting("hibernate.boot.allow_jdbc_metadata_access", "false")
                    .applySetting("hibernate.hbm2ddl.auto", "update")
                    .applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                    .applySetting("hibernate.connection.datasource", hikariDataSource)
                    .applySetting("hibernate.show_sql", "false")
                    .applySetting("hibernate.format_sql", "false")
                    .build();

            return new MetadataSources(serviceRegistry)
                    .addAnnotatedClass(com.radovan.play.entity.ImageEntity.class)
                    .buildMetadata().buildSessionFactory();
        } catch (Throwable exc) {
            System.err.println("Error creating session factory:  " + exc);
            throw new ExceptionInInitializerError(exc);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
