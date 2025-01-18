package com.bada_project.filharmonia;

import com.bada_project.filharmonia.dao.TicketDAO;
import com.bada_project.filharmonia.model.Ticket;
import com.bada_project.filharmonia.model.UserModel;
import com.bada_project.filharmonia.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TicketDAOTest {
    private TicketDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("SYSTEM");
        datasource.setPassword("SYSTEM");
        datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");

        dao = new TicketDAO(new JdbcTemplate(datasource));
    }

    @Test
    void testList() {
        List<Ticket> tickets = dao.list();
        assertFalse(tickets.isEmpty());
    }

    @Test
    void testSave() {
        UserModel user = new UserModel();
        user.setId(101);

        Event event = new Event();
        event.setId(0);

        Ticket ticket = new Ticket(1, "2025-01-01", 100.0, 80.0, "Standard", user, event);
        dao.save(ticket);
    }

    @Test
    void testGet() {
        int id = 1;
        Ticket ticket = dao.get(id);
        assertNotNull(ticket);
        assertEquals(id, ticket.getId());
    }

    @Test
    void testUpdate() {
        UserModel user = new UserModel();
        user.setId(1);

        Event event = new Event();
        event.setId(1);

        Ticket ticket = new Ticket(1, "2025-01-01", 120.0, 100.0, "Premium", user, event);
        dao.update(ticket);

        Ticket updatedTicket = dao.get(ticket.getId());
        assertEquals("Premium", updatedTicket.getCategory());
        assertEquals(120.0, updatedTicket.getGrossPrice());
    }

    @Test
    void testDelete() {
        int id = 1;
        dao.delete(id);

        Exception exception = assertThrows(Exception.class, () -> dao.get(id));
        assertNotNull(exception);
    }
}
