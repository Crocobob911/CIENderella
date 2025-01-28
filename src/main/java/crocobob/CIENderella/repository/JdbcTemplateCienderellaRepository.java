package crocobob.CIENderella.repository;

import crocobob.CIENderella.domain.Content;
import crocobob.CIENderella.domain.Reason;
import crocobob.CIENderella.domain.Writer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateCienderellaRepository implements CienderellaRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateCienderellaRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void addContent(Content content) {

    }

    @Override
    public Optional<Content> getContent() {
        List<Content> result = jdbcTemplate.query(
                "select * form content", contentRowMapper()
        );
        return result.stream().findFirst();
    }

    @Override
    public void deleteContent() {

    }

    @Override
    public void addWriter(Writer writer) {

    }

    @Override
    public Optional<Writer> getAnyWriter() {
        return Optional.empty();
    }

    @Override
    public List<Writer> getAllWriter() {
        return List.of();
    }

    @Override
    public void deleteWriter(long id) {

    }

    @Override
    public void addReason(Reason reason) {

    }

    @Override
    public Optional<Reason> getAnyReason() {
        return Optional.empty();
    }

    @Override
    public List<Reason> getAllReason() {
        return List.of();
    }

    @Override
    public void deleteReason(long id) {

    }

    @Override
    public void clear() {

    }

    private RowMapper<Content> contentRowMapper(){
        return (rs, rowNum) -> {
            Content content = new Content();
            content.setId(rs.getLong("id"));
            content.setTitle(rs.getString("title"));
            return content;
        };
    }
}
