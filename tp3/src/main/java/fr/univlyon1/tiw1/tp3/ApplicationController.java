package fr.univlyon1.tiw1.tp3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.TimeZone;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/8/17.
 */

@SpringBootApplication
@EnableTransactionManagement
public class ApplicationController {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationController.class, args);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Bean
    public DataSource embeddedDB() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
//                .addScript("/db/data.sql")
                .build();
    }

    @Bean
    public LoadTimeWeaver weaver() {
        return new InstrumentationLoadTimeWeaver();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(embeddedDB());
        emfb.setPackagesToScan("fr.univlyon1.tiw1.tp3.modele");
        emfb.setPersistenceUnitName("marchandises-pu");
        emfb.setLoadTimeWeaver(weaver());

        return emfb;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(embeddedDB());
        return jpaTransactionManager;
    }
}
