package be.linyang.kassa.Model.ticket;

import be.linyang.kassa.Model.TicketItem;
import be.linyang.kassa.Model.View;
import be.linyang.kassa.Model.items.ItemType;
import com.fasterxml.jackson.annotation.JsonView;
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
import java.util.Locale;

@Entity("tickets")
public class Ticket {
    @NotSaved
    final private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Id
    private ObjectId id;

	@JsonView(View.Summary.class)
    private int ticketNr;
	@JsonView(View.Summary.class)
    private String ticketIdentifier;

	@JsonView(View.Summary.class)
    private String name;

	@JsonView(View.Summary.class)
    private String date;

	@JsonView(View.Summary.class)
    private String time;

	@JsonView(View.Summary.class)
    private Status status;
	@JsonView(View.Summary.class)
    private PayMethod payMethod;

	@JsonView(View.Summary.class)
    private TicketType ticketType;

	@JsonView(View.Summary.class)
    @Reference
    private List<TicketItem> items;

	@JsonView(View.Summary.class)
    private String tableNr;

	@JsonView(View.Summary.class)
    private boolean isTaken;

    @JsonView(View.Summary.class)
    private boolean isPaid;

    @JsonView(View.Summary.class)
    private double totalPriceWithTax = 0;
    @JsonView(View.Summary.class)
    private double totalPriceWithoutTax = 0;
    @JsonView(View.Summary.class)
    private double totalTaxDrink = 0;
    @JsonView(View.Summary.class)
    private double totalTaxFood = 0;
    @JsonView(View.Summary.class)
    private double totalTaxTakeaway = 0;
    @JsonView(View.Summary.class)
    private double totalTakeaway = 0;
    @JsonView(View.Summary.class)
    private double totalResto = 0;
    @JsonView(View.Summary.class)
    private double totalDrink = 0;
    @JsonView(View.Summary.class)
    private double totalFood = 0;

    public Ticket()
    {
        this.date = LocalDate.now().toString();
        this.time = LocalTime.now().format(timeFormatter);
        this.status = Status.ACTIVE;
        this.items = new LinkedList<>();
        this.payMethod = PayMethod.None;
        this.tableNr = "";
        this.isTaken = false;
        this.isPaid = false;
    }

    public Ticket(TicketType ticketType) {
        this();
        this.ticketType = ticketType;
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
        this.setTicketNr(ticketNr);
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
         this.ticketIdentifier = this.date + '_' + this.ticketNr;
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

    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }

    public boolean isPaid() {
        return this.isPaid;
    }

    public double getTotalPriceWithTax() {
        return totalPriceWithTax;
    }

    public void setTotalPriceWithTax(double totalPriceWithTax) {
        this.totalPriceWithTax = totalPriceWithTax;
    }

    public double getTotalPriceWithoutTax() {
        return totalPriceWithoutTax;
    }

    public void setTotalPriceWithoutTax(double totalPriceWithoutTax) {
        this.totalPriceWithoutTax = totalPriceWithoutTax;
    }

    public double getTotalTaxDrink() {
        return totalTaxDrink;
    }

    public void setTotalTaxDrink(double totalTaxDrink) {
        this.totalTaxDrink = totalTaxDrink;
    }

    public double getTotalTaxFood() {
        return totalTaxFood;
    }

    public void setTotalTaxFood(double totalTaxFood) {
        this.totalTaxFood = totalTaxFood;
    }

    public double getTotalTaxTakeaway() {
        return totalTaxTakeaway;
    }

    public void setTotalTaxTakeaway(double totalTaxTakeaway) {
        this.totalTaxTakeaway = totalTaxTakeaway;
    }

    public void payTicket(PayMethod payMethod) {
        this.setStatus(Status.PAID);
        this.setPayMethod(payMethod);
        this.isPaid = payMethod != PayMethod.None;
    }

    public String getTotalPrice()
    {
        if (this.items == null)
            return "0";
        double total = this.items.stream()
                .mapToDouble(TicketItem::getTotalPrice)
                .sum();
        return String.format (Locale.ENGLISH, "%.2f", total);
    }

    public void deleteTicket() {
        this.status = Status.DELETED;
    }

    public boolean isDeleted() {
        return this.status == Status.DELETED;
    }

    public void addItem(TicketItem item) {
    	this.items.add(item);
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

    public boolean payWithCard() { return payMethod == PayMethod.Card;}

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public boolean isTakeway() {
        return this.tableNr.isEmpty();
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

	public String getTicketIdentifier() {
		return ticketIdentifier;
	}

	public void setTicketIdentifier(String ticketIdentifier) {
		this.ticketIdentifier = ticketIdentifier;
	}

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public double getTotalTakeaway() {
        return totalTakeaway;
    }

    public void setTotalTakeaway(double totalTakeaway) {
        this.totalTakeaway = totalTakeaway;
    }

    public double getTotalResto() {
        return totalResto;
    }

    public void setTotalResto(double totalResto) {
        this.totalResto = totalResto;
    }

    public double getTotalDrink() {
        return totalDrink;
    }

    public void setTotalDrink(double totalDrink) {
        this.totalDrink = totalDrink;
    }

    public double getTotalFood() {
        return totalFood;
    }

    public void setTotalFood(double totalFood) {
        this.totalFood = totalFood;
    }

    public void reCalPrice() {
        this.resetPrice();
        this.items.forEach(i -> {
            this.totalPriceWithTax += i.getTotalPrice();
            this.totalPriceWithoutTax += i.getTotalPriceWithoutTax();
            if (ticketType == TicketType.Takeaway) {
                this.totalTaxTakeaway += i.getTotalTax();
                this.totalTakeaway += i.getTotalPrice();
            } else if (i.getItem().getItemType() == ItemType.Drink) {
                this.totalTaxDrink += i.getTotalTax();
                this.totalDrink += i.getTotalPrice();
            } else {
                this.totalTaxFood += i.getTotalTax();
                this.totalFood += i.getTotalPrice();
            }
        });
    }

    private void resetPrice() {
        this.totalPriceWithTax = 0;
        this.totalPriceWithoutTax = 0;

        this.totalTaxDrink = 0;
        this.totalTaxFood = 0;
        this.totalTaxTakeaway = 0;

        this.totalDrink = 0;
        this.totalFood = 0;
        this.totalTakeaway = 0;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNr=" + ticketNr +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", status=" + status +
                ", payMethod=" + payMethod +
                ", ticketType=" + ticketType +
                ", tableNr='" + tableNr + '\'' +
                ", isTaken=" + isTaken +
                ", isPaid=" + isPaid +
                '}';
    }

    public enum Status{
        ACTIVE,PAID, DELETED
    }

    public enum  TicketType {
        Resto, Takeaway
    }
}
