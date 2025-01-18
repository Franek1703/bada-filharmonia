package com.bada_project.filharmonia.dao;

import com.bada_project.filharmonia.model.Ticket;
import com.bada_project.filharmonia.model.UserModel;
import com.bada_project.filharmonia.model.Event;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TicketDAO {
    private JdbcTemplate jdbcTemplate;

    public TicketDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Helper method to map ResultSet to Ticket
    private Ticket mapRowToTicket(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(rs.getInt("Nr_biletu"));
        ticket.setPurchaseDate(rs.getString("Data_zakupu"));
        ticket.setGrossPrice(rs.getDouble("Cena_brutto"));
        ticket.setNetPrice(rs.getDouble("Cena_netto"));
        ticket.setCategory(rs.getString("Kategoria"));

        UserModel user = new UserModel();
        user.setId(rs.getInt("Nr_klienta"));
        ticket.setUser(user);

        Event event = new Event();
        event.setId(rs.getInt("Nr_wydarzenia"));
        ticket.setEvent(event);

        return ticket;
    }

    public List<Ticket> list() {
        String sql = "SELECT * FROM \"Bilety\"";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToTicket(rs));
    }

    public void save(Ticket ticket) {
        String sql = "INSERT INTO \"Bilety\" (\"Nr_biletu\", \"Data_zakupu\", \"Cena_brutto\", \"Cena_netto\", \"Kategoria\", \"Nr_klienta\", \"Nr_wydarzenia\") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, ticket.getTicketNumber(), ticket.getPurchaseDate(), ticket.getGrossPrice(),
                ticket.getNetPrice(), ticket.getCategory(), ticket.getUser().getId(), ticket.getEvent().getId());
    }

    public Ticket get(int id) {
        String sql = "SELECT * FROM \"Bilety\" WHERE \"Nr_biletu\" = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRowToTicket(rs), id);
    }

    public void update(Ticket ticket) {
        String sql = "UPDATE \"Bilety\" SET \"Data_zakupu\" = ?, \"Cena_brutto\" = ?, \"Cena_netto\" = ?, \"Kategoria\" = ?, \"Nr_klienta\" = ?, \"Nr_wydarzenia\" = ? " +
                "WHERE \"Nr_biletu\" = ?";
        jdbcTemplate.update(sql, ticket.getPurchaseDate(), ticket.getGrossPrice(), ticket.getNetPrice(),
                ticket.getCategory(), ticket.getUser().getId(), ticket.getEvent().getId(), ticket.getTicketNumber());
    }

    public void delete(int id) {
        String sql = "DELETE FROM \"Bilety\" WHERE \"Nr_biletu\" = ?";
        jdbcTemplate.update(sql, id);
    }
}
