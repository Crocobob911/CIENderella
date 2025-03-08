package crocobob.CIENderella.config;

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