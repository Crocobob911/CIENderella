package crocobob.CIENderella;

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
}
