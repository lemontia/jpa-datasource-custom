package kr.jpamulti.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@PropertySource(value = "classpath:application.properties")
@EnableJpaRepositories(
        basePackages = "kr.jpamulti.jpa.alert.repository",
        entityManagerFactoryRef = "alertEntityManagerFactory",
        transactionManagerRef = "alertTransactionManager"
)
public class AlertDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "alert.datasource")
    public DataSource alertDataSource(){
        DataSource build = DataSourceBuilder
                .create()
                .build();
        return build;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean alertEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("alertDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("kr.jpamulti.jpa.alert.entity")
                .build();
    }

    @Bean
    public PlatformTransactionManager alertTransactionManager(@Qualifier("alertEntityManagerFactory") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}

