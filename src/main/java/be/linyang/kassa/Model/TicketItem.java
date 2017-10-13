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

	@JsonView(View.Summary.class)
	private double totalTax = 0;

	@JsonView(View.Summary.class)
	private double totalPriceWithoutTax = 0;

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
        this.calcPrice();
    }

    public boolean removeOne() {
        this.count--;
        this.calcPrice();
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
        calcPrice();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public void setExtras(List<Extra> extras) {
        this.extras = extras;
        calcPrice();
    }

    public void addExtra(Extra extra) {
        this.extras.add(extra);
        calcPrice();
    }

    private void calcPrice() {
	    double totalExtraPrice = extras.stream()
			    .mapToDouble(Extra::getPrice)
			    .sum();

	    this.totalPrice = roundToTwoDecimal((item.getPrice() * this.count) + totalExtraPrice);
	    this.totalPriceWithoutTax = roundToTwoDecimal(this.totalPrice / item.getTaxLevel().getPercent());
	    this.totalTax = this.totalPrice - this.totalPriceWithoutTax;

    }

    public double getTotalPrice() {
    	return this.totalPrice;
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

    private double roundToTwoDecimal(double price) {
	    return BigDecimal.valueOf(price)
			    .setScale(2, RoundingMode.HALF_UP)
			    .doubleValue();
    }

	public double getTotalTax() {
		return totalTax;
	}

	public double getTotalPriceWithoutTax() {
		return totalPriceWithoutTax;
	}
}
