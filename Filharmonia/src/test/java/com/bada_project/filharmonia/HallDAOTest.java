package com.bada_project.filharmonia;

import com.bada_project.filharmonia.dao.HallDAO;
import com.bada_project.filharmonia.model.Hall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HallDAOTest {
    private HallDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("SYSTEM");
        datasource.setPassword("SYSTEM");
        datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");

        dao = new  HallDAO(new JdbcTemplate(datasource));
    }

    @Test
    void testList() {
        List<Hall> listHall = dao.list();
        assertFalse(listHall.isEmpty());
    }

    @Test
    void testSave() {
        Hall hall = new Hall("Main Hall", 200, 2);
        dao.save(hall);
    }

    @Test
    void testGet() {
        int id = 0;
        Hall hall = dao.get(id);
        assertNotNull(hall);
    }

    @Test
    void testUpdate() {
        Hall hall = new Hall("Main Hall", 500, 1);

        dao.update(hall);

    }

    @Test
    void testDelete() {
        int id = 0;
        dao.delete(1);
    }
}


