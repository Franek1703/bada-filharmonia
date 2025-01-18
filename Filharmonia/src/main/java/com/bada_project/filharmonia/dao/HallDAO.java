package com.bada_project.filharmonia.dao;

import com.bada_project.filharmonia.model.Hall;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HallDAO {
    private JdbcTemplate jdbcTemplate;

    public HallDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Hall> list(){
         String sql = "SELECT * FROM \"Sale_koncertowe\"";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Hall hall = new Hall();
            hall.setId(rs.getInt("Nr_sali_koncertowej"));
            hall.setName(rs.getString("Nazwa"));
            hall.setCapacity(rs.getInt("Pojemnosc"));
            return hall;
        });
    }
    public void save(Hall hall) {
        String sql = "INSERT INTO \"Sale_koncertowe\" (\"Nr_sali_koncertowej\", \"Nazwa\", \"Pojemnosc\", \"Typ_sali\", \"Opis\", \"Liczba_wejsc\", \"Nr_filharmonii\") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, hall.getId(), hall.getName(), hall.getCapacity(),
                "Domyślny Typ", null, 0, 0);
    }
    public Hall get(int id) {
        String sql = "SELECT * FROM \"Sale_koncertowe\" WHERE \"Nr_sali_koncertowej\" = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Hall hall = new Hall();
            hall.setId(rs.getInt("Nr_sali_koncertowej"));
            hall.setName(rs.getString("Nazwa"));
            hall.setCapacity(rs.getInt("Pojemnosc"));
            return hall;
        }, id);
    }

    public void update(Hall hall) {
        String sql = "UPDATE \"Sale_koncertowe\" SET \"Nazwa\" = ?, \"Pojemnosc\" = ? WHERE \"Nr_sali_koncertowej\" = ?";
        jdbcTemplate.update(sql, hall.getName(), hall.getCapacity(), hall.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM \"Sale_koncertowe\" WHERE \"Nr_sali_koncertowej\" = ?";
        jdbcTemplate.update(sql, id);
    }

}
