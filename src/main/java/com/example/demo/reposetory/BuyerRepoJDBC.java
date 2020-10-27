package com.example.demo.reposetory;

import com.example.demo.aspect.Loggable;
import com.example.demo.model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BuyerRepoJDBC implements BuyerRepo{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BuyerRepoJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Loggable
    public Iterable<Buyer> findAll() {
        return jdbcTemplate.query("SELECT * FROM buyer", this::mapRowToBuyer);
    }

    @Override
    @Loggable
    public Buyer findById(String Id) {
        return jdbcTemplate.queryForObject("SELECT * FROM buyer where id=?", this::mapRowToBuyer, Id);
    }

    @Override
    @Loggable
    public Buyer save(Buyer buyer) {
        jdbcTemplate.update("INSERT INTO buyer(id, name, country, token) VALUES(?,?,?,?)", buyer.getId(), buyer.getName(),
                buyer.getCountry(), buyer.getToken());
        return buyer;
    }

    private Buyer mapRowToBuyer(ResultSet rs, int rowNum) throws SQLException {
        return new Buyer(rs.getLong("id"), rs.getString("name"), rs.getString("country"),
                rs.getInt("token"));
    }
}
