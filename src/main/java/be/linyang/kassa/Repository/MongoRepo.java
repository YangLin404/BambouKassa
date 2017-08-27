package be.linyang.kassa.Repository;

import be.linyang.kassa.Model.Table;
import be.linyang.kassa.Model.TicketItem;
import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.Model.ticket.Ticket;
import be.linyang.kassa.Model.items.Food;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@DependsOn("embeddedMongo")
@Repository
public class MongoRepo {

    final Morphia morphia = new Morphia();
    final Datastore datastore = morphia.createDatastore(new MongoClient(), "bambou");




    @PostConstruct
    private void init()
    {
        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("be.linyang.kassa.Model");

        datastore.getDB().dropDatabase();

        this.setupTestData();

        datastore.ensureIndexes();


        /*
        Food food = new Food("2","eten2", 20d);
        datastore.save(food);
        List<Ticket> tickets = datastore.createQuery(Ticket.class)
                .field("date")
                .equal(LocalDate.now().toString())
                .asList();
        tickets.get(0).getItems().add(food);
        datastore.save(tickets.get(0));
        */

    }

    private void setupTestData(){

        for (int i=0; i<=10; i++) {
            datastore.save(new Table(String.valueOf(i)));
        }
        List<Table> tables = findAllTables();

        LinkedList<Item> items = new LinkedList<>();
        items.add(new Food("1", "Kippensoep met Chinese champignons","冬菇雞湯", 3.2d));
        items.add(new Food("2", "Kippensoep met champignons", "毛菇雞湯",3d));
        items.add(new Food("3", "Kippensoep met asperges","蘆筍雞湯", 3d));
        items.add(new Food("3", "Kippensoep met bamboe","竹筍雞湯", 3d));

        items.forEach(datastore::save);

        TicketItem ticketItem1 = new TicketItem(items.get(0));
        TicketItem ticketItem2 = new TicketItem(items.get(1));
        TicketItem ticketItem3 = new TicketItem(items.get(2));
        ticketItem2.addOne();
        List<TicketItem> ticketItems = new LinkedList<>();
        ticketItems.add(ticketItem1);
        ticketItems.add(ticketItem2);
        ticketItems.add(ticketItem3);

        List<TicketItem> ticketItems2 = new LinkedList<>();
        ticketItems2.add(ticketItem3);

        datastore.save(ticketItems);
        datastore.save(ticketItems2);

        Ticket ticket = new Ticket();
        ticket.setItems(ticketItems);
        ticket.setTicketNr(1);
        ticket.setTableNr(tables.get(0).getTableNr());

        Ticket ticket2 = new Ticket();
        ticket2.setItems(ticketItems2);
        ticket2.setTicketNr(2);
        ticket2.setTableNr(tables.get(1).getTableNr());

        datastore.save(ticket);
        datastore.save(ticket2);


    }

    public List<Ticket> findAllTickets()
    {
        return datastore.createQuery(Ticket.class)
                .asList();
    }

    public Ticket findActiveTicketByTable(String tableNr)
    {
        return datastore.createQuery(Ticket.class)
                .field("tableNr").equal(tableNr)
                .field("status").equal(Ticket.Status.ACTIVE)
                .get();
    }

    public Ticket createTicket(Ticket ticket) {


        this.datastore.save(ticket);
        Ticket createdTicket = findActiveTicketByTable(ticket.getTableNr());
        return createdTicket;

    }

    public List<Item> findAllItems() {
        return datastore.createQuery(Item.class)
                .asList();
    }

    public List<Table> findAllTables() {
        List<Table> tables = datastore.createQuery(Table.class)
                .asList();
        tables.forEach(t -> t.setTicket(findActiveTicketByTable(t.getTableNr())));
        return tables;
    }

    public List<Ticket> findAllTicketByDate(LocalDate date) {
        return datastore.createQuery(Ticket.class)
                .field("date").equal(date.toString())
                .asList();
    }

    public Ticket findTicketByNr(String ticketNr) {
        return datastore.createQuery(Ticket.class)
                .field("ticketNr").equal(ticketNr)
                .get();
    }
}
