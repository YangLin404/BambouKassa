package be.linyang.kassa.Repository;

import be.linyang.kassa.Model.TicketItem;
import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.Model.ticket.Ticket;
import be.linyang.kassa.Model.items.Drink;
import be.linyang.kassa.Model.items.Food;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
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

    private List<Item> items;


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
        LinkedList<Item> items = new LinkedList<>();
        items.add(new Food("1", "Kippensoep met Chinese champignons", 3.2d));
        items.add(new Food("2", "Kippensoep met champignons", 3d));
        items.add(new Food("3", "Kippensoep met asperges", 3d));
        items.add(new Food("3", "Kippensoep met bamboe", 3d));

        items.forEach(datastore::save);

        TicketItem ticketItem1 = new TicketItem(items.get(0));
        TicketItem ticketItem2 = new TicketItem(items.get(1));
        ticketItem2.addOne();
        List<TicketItem> ticketItems = new LinkedList<>();
        ticketItems.add(ticketItem1);
        ticketItems.add(ticketItem2);

        datastore.save(ticketItems);

        Ticket ticket = new Ticket();
        ticket.setItems(ticketItems);
        ticket.setTicketNr(ticket.getDate() + "/1");

        datastore.save(ticket);
    }

    private void loadData(){
        this.items = getAllItems();
    }

    public List<Ticket> getAllTickets()
    {
        return datastore.createQuery(Ticket.class)
                .asList();
    }

    public List<Item> getAllItems()
    {
        return datastore.createQuery(Item.class)
                .asList();
    }


}
