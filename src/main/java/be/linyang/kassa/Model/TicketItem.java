package be.linyang.kassa.Model;

import be.linyang.kassa.Model.items.Extra;
import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.Model.ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonView;
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

	@JsonView(View.Summary.class)
    @Reference
    private Item item;

	@JsonView(View.Summary.class)
    private int count;

	@JsonView(View.Summary.class)
    @Reference
    private List<Extra> extras;

	@JsonView(View.Summary.class)
    private double totalPrice = 0;

    public TicketItem(){
        this.extras = new LinkedList<>();
    }

    public TicketItem(Item item)
    {
        this();
	    this.count = 1;
        this.setItem(item);
    }

    public void addOne()
    {
        this.count++;
        this.totalPrice = getTotalPrice();
    }

    public boolean removeOne() {
        this.count--;
	    this.totalPrice = getTotalPrice();
        return this.count == 0;
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
        this.totalPrice = getTotalPrice();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public void setExtras(List<Extra> extras) {
        this.extras = extras;
        this.totalPrice = getTotalPrice();
    }

    public void addExtra(Extra extra) {
        this.extras.add(extra);
        this.totalPrice = getTotalPrice();
    }

    public double getTotalPrice() {
        double extraPrice = extras.stream()
                                .mapToDouble(Extra::getPrice)
                                .sum();
        return BigDecimal.valueOf((item.getPrice() * count)+extraPrice)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public boolean isMaxExtra() {
        return this.extras.size() == count;
    }

    public void replaceLastExtra(Extra extra) {
        if (this.extras.size() > 0) {
            this.extras.remove(this.extras.size() - 1);
            this.addExtra(extra);
        }
    }


}
