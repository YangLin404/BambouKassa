package be.linyang.kassa.Model;

import be.linyang.kassa.Model.ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonView;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.NotSaved;

@Entity("tables")
public class Table {

    @Id
    private ObjectId id;
	@JsonView(View.Summary.class)
    private String tableNr;
    @NotSaved
    private Ticket ticket;
    @NotSaved
    @JsonView(View.Summary.class)
    private int ticketNr;


    public Table(){}

    public Table(String tableNr) {
        this.tableNr = tableNr;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTableNr() {
        return tableNr;
    }

    public void setTableNr(String tableNr) {
        this.tableNr = tableNr;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public boolean isTableEmpty() {
        return this.ticket == null;
    }

	public int getTicketNr() {
		return ticketNr;
	}

	public void setTicketNr(int ticketNr) {
		this.ticketNr = ticketNr;
	}
}
