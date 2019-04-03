package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class PersonDaoJdbc implements PersonDao {

    private final NamedParameterJdbcOperations jdbc;

    public PersonDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        final HashMap<String, Object> params = new HashMap<>(1);
        return jdbc.queryForObject("select count(*) from persons",params , Integer.class );
    }

    @Override
    public void insert(Person person) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id",person.getId());
        params.put("name",person.getName());

        jdbc.update("insert into persons (id, `name`) values (:id, :name)", params);
    }

    @Override
    public Person getById(int id) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id",id);
        return jdbc.queryForObject("select * from persons where id = :id", params, new PersonMapper());
    }

    @Override
    public List<Person> getAll() {
        return jdbc.query("select * from persons", new PersonMapper());
    }

    private static class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Person(id, name);
        }
    }
}
