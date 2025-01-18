package com.bada_project.filharmonia.dao;

import com.bada_project.filharmonia.model.Event;
import com.bada_project.filharmonia.model.Hall;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EventDAO {
    private JdbcTemplate jdbcTemplate;

    public EventDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Helper method to map ResultSet to Event
    private Event mapRowToEvent(ResultSet rs) throws SQLException {
        Event event = new Event();
        event.setId(rs.getInt("Nr_wydarzenia"));
        event.setDate(rs.getString("Data"));
        event.setName(rs.getString("Nazwa"));
        event.setDescription(rs.getString("Opis"));

        // Map Hall
        Hall hall = new Hall();
        hall.setId(rs.getInt("Nr_sali_koncertowej"));
        event.setHall(hall);

        return event;
    }

    public List<Event> list() {
        String sql = "SELECT * FROM \"Wydarzenia\"";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToEvent(rs));
    }

    public void save(Event event) {
        String sql = "INSERT INTO \"Wydarzenia\" (\"Nr_wydarzenia\", \"Data\", \"Nazwa\", \"Opis\", \"Nr_sali_koncertowej\") " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, event.getId(), event.getDate(), event.getName(),
                event.getDescription(), event.getHall().getId());
    }

    public Event get(int id) {
        String sql = "SELECT * FROM \"Wydarzenia\" WHERE \"Nr_wydarzenia\" = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRowToEvent(rs), id);
    }

    public void update(Event event) {
        String sql = "UPDATE \"Wydarzenia\" SET \"Data\" = ?, \"Nazwa\" = ?, \"Opis\" = ?, \"Nr_sali_koncertowej\" = ? " +
                "WHERE \"Nr_wydarzenia\" = ?";
        jdbcTemplate.update(sql, event.getDate(), event.getName(), event.getDescription(),
                event.getHall().getId(), event.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM \"Wydarzenia\" WHERE \"Nr_wydarzenia\" = ?";
        jdbcTemplate.update(sql, id);
    }
}
