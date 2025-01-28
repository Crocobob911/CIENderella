package crocobob.CIENderella;

import crocobob.CIENderella.repository.CienderellaRepository;
import crocobob.CIENderella.repository.JdbcTemplateCienderellaRepository;
import crocobob.CIENderella.repository.JpaCienderellaRepository;
import crocobob.CIENderella.service.DataManagementService;
import org.springframework.context.annotation.Bean;

import jakarta.persistence.EntityManager;
import javax.sql.DataSource;

public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;


    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public DataManagementService dataManagementService() {
        return new DataManagementService(cienderellaRepository());
    }

    @Bean
    public CienderellaRepository cienderellaRepository() {
//        return new JpaCienderellaRepository(em);
        return new JdbcTemplateCienderellaRepository(dataSource);
    }

}
