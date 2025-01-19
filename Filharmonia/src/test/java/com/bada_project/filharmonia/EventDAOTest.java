package com.bada_project.filharmonia;

import com.bada_project.filharmonia.dao.EventDAO;
import com.bada_project.filharmonia.dao.HallDAO;
import com.bada_project.filharmonia.model.Event;
import com.bada_project.filharmonia.model.Hall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventDAOTest {
    private EventDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("SYSTEM");
        datasource.setPassword("SYSTEM");
        datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");

        dao = new EventDAO(new JdbcTemplate(datasource));
    }

    @Test
    void testList() {
        List<Event> listEvent = dao.list();
        assertFalse(listEvent.isEmpty());
    }

    @Test
    void testSave() {
        Event event = new Event(1, "2025-01-20", "Concert A", "Description for Concert A", new Hall("Main Hall", 500,0));
        dao.save(event);
    }

    @Test
    void testGet() {

    }

    @Test
    void testUpdate() {


    }

    @Test
    void testDelete() {

    }
}


