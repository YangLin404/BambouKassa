package be.linyang.kassa;


import be.linyang.kassa.Model.Table;
import be.linyang.kassa.Model.TicketItem;
import be.linyang.kassa.Model.items.Extra;
import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.Model.items.ItemType;
import be.linyang.kassa.Model.ticket.PayMethod;
import be.linyang.kassa.Model.ticket.Ticket;
import be.linyang.kassa.Repository.MongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestoManager {

    @Autowired
    private MongoRepo mongoRepo;

    private List<Extra> extras;
    private List<Item> items;
    private List<Table> tables;
    private List<Ticket> ticketsToday;
    private int ticketNrSequence;

    @PostConstruct
    private void init() {
        loadData();
    }

    public List<Table> getTables() {
        return this.tables;
    }

    public List<Ticket> getTodaysTakewayTicket() {
        return ticketsToday.stream()
                .filter(Ticket::isTakeway)
                .collect(Collectors.toList());
    }

    public List<Item> getItems() { return this.items;}

    public List<Extra> getExtras() {
        return this.extras;
    }

    private void loadData() {
        extras = mongoRepo.findAllExtra();
        tables = mongoRepo.findAllTables();
        items = mongoRepo.findAllItems();

        ticketsToday = mongoRepo.findAllTicketByDate(LocalDate.now());
        ticketNrSequence = ticketsToday.size();


    }

    public Ticket createTicketOnTable(String tableNr) {
	    Ticket ticket = new Ticket();
	    ticket.setTableNr(tableNr);
	    return createTicket(ticket);

    }

    public boolean changeTable(String fromTableNr, String toTableNr) {
        Table fromTable = findTable(fromTableNr);
        Table toTable = findTable(toTableNr);
        if (fromTable != null && toTable != null) {
            Ticket fromTicket = fromTable.getTicket();
            if (toTable.getTicket() != null) {
                Ticket toTicket = toTable.getTicket();
                fromTable.setTicket(toTicket);
                toTicket.setTableNr(fromTable.getTableNr());
                mongoRepo.saveTicket(toTicket);
            }
            toTable.setTicket(fromTicket);
            fromTicket.setTableNr(toTable.getTableNr());
            mongoRepo.saveTicket(fromTicket);
            return true;

        } else {
            return false;
        }
    }

    public Ticket createTicket(Ticket ticket) {
        Table table = this.tables.stream()
                .filter(t -> t.getTableNr().equals(ticket.getTableNr()))
                .findFirst()
                .get();
        ticket.setTicketType(Ticket.TicketType.Resto);
        this.ticketsToday.add(ticket);
        ticket.setTicketNr(++ticketNrSequence);
        mongoRepo.createTicket(ticket);
        table.setTicket(ticket);
        table.setTicketNr(ticket.getTicketNr());
        return ticket;
    }

    public Ticket createTakewayTicket() {
        Ticket ticket = new Ticket(Ticket.TicketType.Takeaway);
        ticket.setTicketNr(++ticketNrSequence);
        this.ticketsToday.add(ticket);
        mongoRepo.createTicket(ticket);
        return ticket;
    }

    public Ticket updateTicket(int ticketNr, String name, String time) {
        Ticket ticket = findTodayTicketByNr(ticketNr);
        ticket.setName(name);
        ticket.setTime(time);
        mongoRepo.saveTicket(ticket);
        return ticket;
    }

    public Ticket updateTicketName(int ticketNr, String name) {
        Ticket ticket = findTodayTicketByNr(ticketNr);
        ticket.setName(name);
        mongoRepo.saveTicket(ticket);
        return ticket;
    }

    public Ticket updateTicketTime(int ticketNr, String time) {
        Ticket ticket = findTodayTicketByNr(ticketNr);
        ticket.setTime(time);
        mongoRepo.saveTicket(ticket);
        return ticket;
    }

    public Ticket updateTicketTaken(int ticketNr, boolean taken) {
        Ticket ticket = findTodayTicketByNr(ticketNr);
        ticket.setTaken(taken);
        mongoRepo.saveTicket(ticket);
        return ticket;
    }

    public boolean updateTicketItemRemark(int ticketNr, String quicklink, String remark){
        Ticket ticket = findTodayTicketByNr(ticketNr);
        Item item = findItem(quicklink);
        if (item != null) {
            TicketItem ticketItem = findTicketItemByItem(ticket.getItems(), item);
            ticketItem.setRemark(remark);
            mongoRepo.saveTicketItem(ticketItem);
            return true;
        } else {
            return false;
        }
    }

    public Ticket addExtraToItem(int ticketNr, String quicklink, String extra) {
        Ticket ticket = findTodayTicketByNr(ticketNr);
        Item item = findItem(quicklink);
        if (item.getItemType() == ItemType.MainDishe) {
            TicketItem ticketItem = findTicketItemByItem(ticket.getItems(), item);
            Extra extraToAdd = extras.stream()
                    .filter(e -> e.getName().toLowerCase().equals(extra.toLowerCase()))
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
            ticket.reCalPrice();
            mongoRepo.saveTicket(ticket);
        }
        return ticket;
    }

    public Ticket payTicket(int ticketNr, String payMethod) {
        Ticket ticket = findTodayTicketByNr(ticketNr);
        ticket.payTicket(PayMethod.valueOf(payMethod));
        removeTicketFromTable(ticket.getTableNr());
        mongoRepo.saveTicket(ticket);
        return ticket;
    }

    private void removeTicketFromTable(String tableNr) {
        Table table = this.tables.stream()
                .filter(t -> t.getTableNr().equalsIgnoreCase(tableNr))
                .findFirst()
                .orElse(null);
        if (table != null) {
            table.setTicket(null);
            table.setTicketNr(0);
        }
    }

    public boolean deleteTodayTicket(int ticketNr) {
        Ticket ticket = findTodayTicketByNr(ticketNr);
        if (ticket != null) {
            mongoRepo.deleteTicket(ticket);
            this.ticketsToday.remove(ticket);
            if (!(ticket.getTableNr().isEmpty() || ticket.getTableNr().equals("0"))) {
                this.removeTicketFromTable(ticket.getTableNr());
            }
            return true;
        } else
            return false;
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

    public Ticket addItemToTicket(int ticketNr, String quicklink) {
        Ticket ticket = findTodayTicketByNr(ticketNr);
        if (ticket == null)
            return null;
        Item item = findItem(quicklink);
        if (item == null)
            return null;
        else {
            TicketItem ticketItem = findTicketItemByItem(ticket.getItems(),item);
            if (ticketItem == null) {
                TicketItem ticketItem1 = new TicketItem(item);
                mongoRepo.saveTicketItem(ticketItem1);
                ticket.addItem(ticketItem1);
            } else {
                ticketItem.addOne();
                mongoRepo.saveTicketItem(ticketItem);
            }


            ticket.reCalPrice();
            mongoRepo.saveTicket(ticket);
        }
            return ticket;
    }

    public Ticket removeItemFromTicket(int ticketNr, String quicklink) {
        Ticket ticket = findTodayTicketByNr(ticketNr);
        if (ticket == null)
            return null;
        Item item = findItem(quicklink);
        if (item == null)
            return null;
        else {
            TicketItem ticketItem = findTicketItemByItem(ticket.getItems(),item);
            if (ticketItem != null) {
                if (ticketItem.removeOne()) {
                    //if ticketItem is empty
                    mongoRepo.deleteTicketItem(ticketItem);
                    ticket.getItems().remove(ticketItem);
                } else {
                    ticketItem.removeLastExtra();
                    mongoRepo.saveTicketItem(ticketItem);
                }
            }
        }
        ticket.reCalPrice();
        mongoRepo.saveTicket(ticket);
        return ticket;
    }

    public Ticket findTodayTicketByNr(int ticketNr) {
        return ticketsToday.stream()
                .filter(t -> t.getTicketNr() == ticketNr)
                .findFirst()
                .orElse(null);
    }

    private boolean isMaindishe(String quicklink) {
        Item item = items.stream()
                .filter(i -> i.getQuicklink().equals(quicklink))
                .findFirst()
                .orElse(null);
        if (item == null || item.getItemType() != ItemType.MainDishe)
            return false;
        else
            return true;
    }

    public List<Ticket> getTicketByDate(String date, String filter) {
        LocalDate dateToSearch = LocalDate.parse(date);
        List<Ticket> tickets;
        if (filter.isEmpty()) {
                tickets = mongoRepo.findAllTicketByDate(dateToSearch);
            } else {
                PayMethod payMethod = PayMethod.valueOf(filter);
                tickets = mongoRepo.findAllTicketByDate(dateToSearch, payMethod);
            }
        return tickets;

    }

    public List<Ticket> getTicketByDate(String date) {
        LocalDate dateToSearch = LocalDate.parse(date);
        return mongoRepo.findAllTicketByDate(dateToSearch);
    }

    public boolean addItem(Item item) {
        this.mongoRepo.addItem(item);
        this.items.add(item);
        return true;

    }

    public boolean reloadData() {
        this.loadData();
        return true;
    }

    private Table findTable(String tableNr) {
        return this.tables.stream()
                .filter(t -> t.getTableNr().equalsIgnoreCase(tableNr))
                .findFirst()
                .orElse(null);
    }

    private Item findItem(String quicklink) {
        return items.stream()
                .filter(t -> t.getQuicklink().equalsIgnoreCase(quicklink))
                .findFirst()
                .orElse(null);
    }

    private TicketItem findTicketItemByItem(List<TicketItem> items, Item item) {
        return items.stream()
                .filter(i -> i.getItem().equals(item))
                .findFirst()
                .orElse(null);
    }
    /*
    private boolean isDrink(String str) {
        if(str.matches("\\d+"))
            return false;
        else {
            if (!str.matches("\\d"))
                return false;
            else
                return true;
        }
    }
    */
}
