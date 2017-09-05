package be.linyang.kassa.Model.ticket;

import be.linyang.kassa.Model.TicketItem;
import be.linyang.kassa.Model.items.Item;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.NotSaved;
import org.mongodb.morphia.annotations.Reference;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Entity("tickets")
public class Ticket {
    @NotSaved
    final private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Id
    private ObjectId id;

    private int ticketNr;

    private String name;

    private String date;
    private String time;

    private Status status;
    private PayMethod payMethod;

    @Reference
    private List<TicketItem> items;


    private String tableNr;

    public Ticket()
    {
        this.date = LocalDate.now().toString();
        this.time = LocalTime.now().format(timeFormatter);
        this.status = Status.ACTIVE;
        this.items = new LinkedList<>();
        this.payMethod = PayMethod.Cash;
        this.tableNr = "";
    }

    public Ticket(String date, List<TicketItem> items) {
        this();
        this.date = date;
        this.items = items;
        this.name = "";
    }

    public Ticket(String date, List<TicketItem> items, String name) {
        this();
        this.date = date;
        this.items = items;
        this.name = name;
    }

    public Ticket(int ticketNr, String name, List<TicketItem> items, String tableNr) {
        this();
        this.ticketNr = ticketNr;
        this.name = name;
        this.items = items;
        this.tableNr = tableNr;
    }

    public ObjectId getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public List<TicketItem> getItems() {
        return items;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setItems(List<TicketItem> items) {
        this.items = items;
    }

    public int getTicketNr() {
        return ticketNr;
    }

    public String getTime() {
        return time;
    }

    public void setTicketNr(int ticketNr) {
         this.ticketNr = ticketNr;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTable() {
        return tableNr;
    }

    public void setTableNr(String tableNr) {
        this.tableNr = tableNr;
    }

    public void payTicket(PayMethod payMethod) {
        this.setStatus(Status.PAID);
        this.setPayMethod(payMethod);
    }

    public double getTotalPrice()
    {
        if (this.items == null)
            return 0d;
        return this.items.stream()
                .mapToDouble(TicketItem::getTotalPrice)
                .sum();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTableNr() {
        return tableNr;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public boolean isTakeway() {
        return this.tableNr.isEmpty();
    }


    public enum Status{
        ACTIVE,PAID;
    }
}
