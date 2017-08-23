package be.linyang.kassa.Model.ticket;

import be.linyang.kassa.Model.TicketItem;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity("tickets")
public class Ticket {

    @Id
    private ObjectId id;

    private String ticketNr;

    private String name;

    private String date;
    private String time;

    @Reference
    private List<TicketItem> items;

    public Ticket()
    {
        this.date = LocalDate.now().toString();
        this.time = LocalTime.now().toString();
    }

    public Ticket(String date, List<TicketItem> items) {
        this.date = date;
        this.items = items;
        this.name = "";
    }

    public Ticket(String date, List<TicketItem> items, String name) {
        this.date = date;
        this.items = items;
        this.name = name;
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

    public String getTicketNr() {
        return ticketNr;
    }

    public String getTime() {
        return time;
    }

    public void setTicketNr(String ticketNr) {
        this.ticketNr = ticketNr;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTotalPrice()
    {
        return this.items.stream()
                .mapToDouble(TicketItem::getTotalPrice)
                .sum();
    }
}
