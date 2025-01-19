package com.bada_project.filharmonia;

import com.bada_project.filharmonia.dao.EventDAO;
import com.bada_project.filharmonia.dao.TicketDAO;
import com.bada_project.filharmonia.model.Ticket;
import com.bada_project.filharmonia.model.UserModel;
import com.bada_project.filharmonia.model.Event;
import com.bada_project.filharmonia.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TicketDAOTest {
    private TicketDAO dao;

    private EventDAO eventDAO;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("SYSTEM");
        datasource.setPassword("SYSTEM");
        datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");

        dao = new TicketDAO(new JdbcTemplate(datasource));
        eventDAO = new EventDAO(new JdbcTemplate(datasource));
    }

    @Test
    void testList() {
        List<Ticket> tickets = dao.list();
        assertFalse(tickets.isEmpty());
    }

    @Test
    void testListByEventId() {
        List<Ticket> tickets = dao.listByEventId(0);
        System.out.println(tickets.size());
        assertFalse(tickets.isEmpty());
    }

    @Test
    void testListByUSerId() {
        List<Ticket> tickets = dao.listByUserId(104);
        System.out.println(tickets.size());
        assertFalse(tickets.isEmpty());
    }

    @Test
    void testTicketService() {
        TicketService service = new TicketService(dao, eventDAO);
        List<Ticket> tickets1 = service.getCurrentTickets(101);

        List<Ticket> tickets2 = service.getExpiredTickets(101);

        System.out.println(tickets1.size());

        System.out.println(tickets2.size());

    }

    @Test
    void testFilteringLogic() {
        List<Ticket> tickets = new ArrayList<>();

        // Add future ticket
        Event futureEvent = new Event();
        futureEvent.setDate("2025-01-20");
        Ticket futureTicket = new Ticket();
        futureTicket.setEvent(futureEvent);
        tickets.add(futureTicket);

        // Add expired ticket
        Event pastEvent = new Event();
        pastEvent.setDate("2024-01-01");
        Ticket pastTicket = new Ticket();
        pastTicket.setEvent(pastEvent);
        tickets.add(pastTicket);

        TicketService service = new TicketService(dao, eventDAO);

        List<Ticket> currentTickets = tickets.stream()
                .filter(ticket -> LocalDate.parse(ticket.getEvent().getDate()).isAfter(LocalDate.now()))
                .collect(Collectors.toList());

        List<Ticket> expiredTickets = tickets.stream()
                .filter(ticket -> LocalDate.parse(ticket.getEvent().getDate()).isBefore(LocalDate.now()))
                .collect(Collectors.toList());

        System.out.println("Future Tickets: " + currentTickets.size());
        System.out.println("Expired Tickets: " + expiredTickets.size());
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
