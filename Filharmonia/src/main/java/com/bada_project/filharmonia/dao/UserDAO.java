package com.bada_project.filharmonia.dao;

import com.bada_project.filharmonia.model.UserModel;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO {
    private JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private UserModel mapRowToUser(ResultSet rs) throws SQLException {
        UserModel user = new UserModel();
        user.setId(rs.getInt("Nr_klienta"));
        user.setFirstName(rs.getString("Imie"));
        user.setLastName(rs.getString("Nazwisko"));
        user.setPhoneNumber(rs.getString("Telefon"));
        return user;
    }

    public List<UserModel> list() {
        String sql = "SELECT * FROM \"Klienci\"";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToUser(rs));
    }

    public void save(UserModel user) {
        String sql = "INSERT INTO \"Klienci\" (\"Nr_klienta\", \"Imie\", \"Nazwisko\", \"Telefon\", \"Nr_filharmonii\") " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), 0);
    }

    public UserModel get(int id) {
        String sql = "SELECT * FROM \"Klienci\" WHERE \"Nr_klienta\" = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRowToUser(rs), id);
    }

    public void update(UserModel user) {
        String sql = "UPDATE \"Klienci\" SET \"Imie\" = ?, \"Nazwisko\" = ?, \"Telefon\" = ? WHERE \"Nr_klienta\" = ?";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM \"Klienci\" WHERE \"Nr_klienta\" = ?";
        jdbcTemplate.update(sql, id);
    }
}
