package be.linyang.kassa;


import be.linyang.kassa.Model.Table;
import be.linyang.kassa.Model.TicketItem;
import be.linyang.kassa.Model.items.Extra;
import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.Model.ticket.PayMethod;
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

    private List<Extra> extras;
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

    public List<Item> getItems() { return this.items;}

    private void loadData() {
        extras = mongoRepo.findAllExtra();
        tables = mongoRepo.findAllTables();
        items = mongoRepo.findAllItems();
        ticketsToday = mongoRepo.findAllTicketByDate(LocalDate.now());

    }

    public Ticket createTicket(Ticket ticket) {
        Table table = this.tables.stream()
                .filter(t -> t.getTableNr().equals(ticket.getTableNr()))
                .findFirst()
                .get();
        this.ticketsToday.add(ticket);
        ticket.setTicketNr(this.ticketsToday.size());
        mongoRepo.createTicket(ticket);
        table.setTicket(ticket);
        return ticket;
    }

    public Ticket addExtraToItem(String ticketNr, String quicklink, String extra) {
        Ticket ticket = findTodayTicketByNr(ticketNr);
        TicketItem ticketItem = ticket.getItems().stream()
                .filter(t -> t.getItem().getQuicklink().equals(quicklink))
                .findFirst()
                .orElse(null);
        Extra extraToAdd = extras.stream()
                .filter(e -> e.getName().equals(extra))
                .findFirst()
                .orElse(null);
        if (ticketItem.isMaxExtra()) {
            ticketItem.replaceLastExtra(extraToAdd);
        } else {
            if (ticketItem == null || extraToAdd == null)
                return null;
            else {
                ticketItem.addExtra(extraToAdd);
                mongoRepo.saveTicketItem(ticketItem);
            }
        }
        return ticket;
    }

    public Ticket payTicket(String ticketNr, String payMethod) {
        Ticket ticket = findTodayTicketByNr(ticketNr);
        ticket.payTicket(PayMethod.valueOf(payMethod));
        mongoRepo.saveTicket(ticket);
        return ticket;
    }

    public Ticket getTicketByNr(String ticketNr) {
        return mongoRepo.findTicketByNr(ticketNr);
    }

    public Ticket getActiveTicketOfTable(String tableNr) {
        return ticketsToday.stream()
                .filter(t -> t.getTableNr().equals(tableNr))
                .filter(t -> t.getStatus().equals(Ticket.Status.ACTIVE))
                .findFirst()
                .orElse(null);

    }

    public Ticket addItemToTicket(String ticketNr, String itemQL) {
        Ticket ticket = findTodayTicketByNr(ticketNr);
        if (ticket == null)
            return null;
        Item item = items.stream()
                .filter(t -> t.getQuicklink().equals(itemQL))
                .findFirst()
                .orElse(null);
        if (item == null)
            return null;

        TicketItem ticketItem = ticket.getItems().stream()
                .filter(i -> i.getItem().equals(item))
                .findFirst()
                .orElse(null);
        if (ticketItem == null) {
            TicketItem ticketItem1 = new TicketItem(item);
            mongoRepo.saveTicketItem(ticketItem1);
            ticket.getItems().add(ticketItem1);
            mongoRepo.saveTicket(ticket);
        }
        else {
            ticketItem.addOne();
            mongoRepo.saveTicketItem(ticketItem);
            mongoRepo.saveTicket(ticket);
        }

        return ticket;
    }

    private Ticket findTodayTicketByNr(String ticketNr) {
        return ticketsToday.stream()
                .filter(t -> t.getTicketNr().equals(ticketNr))
                .findFirst()
                .orElse(null);
    }
}
