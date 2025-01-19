package com.bada_project.filharmonia.dao;

import com.bada_project.filharmonia.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAO {

    @Autowired
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

    public UserModel save(UserModel user) {
        String getMaxIdSql = "SELECT COALESCE(MAX(\"Nr_klienta\"), 0) FROM \"Klienci\"";
        int newId = jdbcTemplate.queryForObject(getMaxIdSql, Integer.class) + 1;

        user.setId(newId);

        String sql = "INSERT INTO \"Klienci\" (\"Nr_klienta\", \"Imie\", \"Nazwisko\", \"Telefon\", \"Nr_filharmonii\") " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), 0);

        return user;
    }

    public UserModel get(int id) {
        String sql = "SELECT * FROM \"Klienci\" WHERE \"Nr_klienta\" = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRowToUser(rs), id);
    }

    public UserModel getByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM \"Klienci\" WHERE \"Telefon\" = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRowToUser(rs), phoneNumber);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
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
