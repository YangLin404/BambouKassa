package be.linyang.kassa.Repository;

import be.linyang.kassa.Model.Table;
import be.linyang.kassa.Model.TicketItem;
import be.linyang.kassa.Model.items.Extra;
import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.Model.items.ItemType;
import be.linyang.kassa.Model.ticket.Ticket;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

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

        for (int i=1; i<=10; i++) {
            datastore.save(new Table(String.valueOf(i)));
        }
        List<Table> tables = findAllTables();

        LinkedList<Extra> extras = new LinkedList<>();

        extras.add(new Extra("rijst",2d));
        extras.add(new Extra("friet",2d));
        extras.add(new Extra("nasi",2d));
        extras.add(new Extra("bami",2d));
        extras.add(new Extra("mihoen",2d));
        datastore.save(extras);

        LinkedList<Item> items = new LinkedList<>();
        items.add(new Item("1", "Kippensoep met Chinese champignons","冬菇雞湯", 3.2d, ItemType.Soup));
        items.add(new Item("2", "Kippensoep met champignons", "毛菇雞湯",3d, ItemType.Soup));
        items.add(new Item("3", "Kippensoep met asperges","蘆筍雞湯", 3d, ItemType.Soup));
        items.add(new Item("4", "Kippensoep met bamboe","竹筍雞湯", 3d, ItemType.Soup));
        items.add(new Item("58","Rundvlees in currysaus", "test",10d, ItemType.MainDishe));

        datastore.save(items);

        TicketItem ticketItem1 = new TicketItem(items.get(0));
        TicketItem ticketItem2 = new TicketItem(items.get(1));
        TicketItem ticketItem3 = new TicketItem(items.get(2));
        TicketItem ticketItem4 = new TicketItem(items.get(4));
        ticketItem2.addOne();
        ticketItem4.setExtras(extras.subList(0,0));
        List<TicketItem> ticketItems = new LinkedList<>();
        ticketItems.add(ticketItem1);
        ticketItems.add(ticketItem2);
        ticketItems.add(ticketItem3);
        ticketItems.add(ticketItem4);

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

    public void createTicket(Ticket ticket) {
        this.datastore.save(ticket);
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

    public List<Extra> findAllExtra() {
        return datastore.createQuery(Extra.class)
                .asList();
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

    public void saveTicketItem(TicketItem ticketItem){
        this.datastore.save(ticketItem);
    }

    public void saveTicket(Ticket ticket){
        this.datastore.save(ticket);
    }


}
