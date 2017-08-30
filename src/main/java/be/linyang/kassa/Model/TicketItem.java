package be.linyang.kassa.Model;

import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.Model.ticket.Ticket;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity("ticketItem")
public class TicketItem {

    @Id
    private ObjectId id;

    @Reference
    private Item item;

    private int count;

    public TicketItem(){

    }

    public TicketItem(Item item)
    {
        this.item = item;
        this.count = 1;
    }

    public TicketItem(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    public void addOne()
    {
        this.count++;
    }

    public ObjectId getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public double getTotalPrice() {
        return BigDecimal.valueOf(item.getPrice() * count)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
