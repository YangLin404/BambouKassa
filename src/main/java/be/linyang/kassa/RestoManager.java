package be.linyang.kassa;


import be.linyang.kassa.Model.Table;
import be.linyang.kassa.Model.TicketItem;
import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.Model.ticket.Ticket;
import be.linyang.kassa.Repository.MongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Service
public class RestoManager {

    @Autowired
    private MongoRepo mongoRepo;

    private List<Item> items;
    private List<Table> tables;
    private List<Ticket> ticketsToday;

    @PostConstruct
    private void init() {
        loadData();
    }

    public List<Table> getTables() {
        return this.tables;
    }

    private void loadData() {
        tables = mongoRepo.findAllTables();
        items = mongoRepo.findAllItems();
        ticketsToday = mongoRepo.findAllTicketByDate(LocalDate.now());

    }

    public Ticket addTicket(Ticket ticket) {
        Table table = this.tables.stream()
                .filter(t -> t.getTableNr().equals(ticket.getTableNr()))
                .findFirst()
                .get();
        this.ticketsToday.add(ticket);
        ticket.setTicketNr(this.ticketsToday.size());
        Ticket createdTicket = mongoRepo.createTicket(ticket);
        table.setTicket(createdTicket);
        return createdTicket;
    }

    public Ticket getTicketByNr(String ticketNr) {
        return mongoRepo.findTicketByNr(ticketNr);
    }

    public Ticket getActiveTicketOfTable(String tableNr) {
        return mongoRepo.findActiveTicketByTable(tableNr);
    }

    public Ticket addItemToTicket(String ticketNr, String itemQL) {
        Ticket ticket = ticketsToday.stream()
                .filter(t -> t.getTicketNr().equals(ticketNr))
                .findFirst()
                .get();
        Item item = items.stream()
                .filter(t -> t.getQuicklink().equals(itemQL))
                .findFirst()
                .get();
        TicketItem ticketItem = new TicketItem();

        return ticket;

    }
}
