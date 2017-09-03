package be.linyang.kassa.Model;

import be.linyang.kassa.Model.items.Extra;
import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.Model.ticket.Ticket;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

@Entity("ticketItem")
public class TicketItem {

    @Id
    private ObjectId id;

    @Reference
    private Item item;

    private int count;

    @Reference
    private List<Extra> extras;

    public TicketItem(){
        this.extras = new LinkedList<>();
    }

    public TicketItem(Item item)
    {
        this();
        this.item = item;
        this.count = 1;
    }

    public TicketItem(Item item, int count) {
        this();
        this.item = item;
        this.count = count;
    }

    public TicketItem(Item item, int count, List<Extra> extras) {
        this(item, count);
        this.extras = extras;
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

    public void setItem(Item item) {
        this.item = item;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }

    public void addExtra(Extra extra) {
        this.extras.add(extra);
    }

    public double getTotalPrice() {
        double extraPrice = extras.stream()
                                .mapToDouble(Extra::getPrice)
                                .sum();
        return BigDecimal.valueOf((item.getPrice() * count)+extraPrice)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }


}
