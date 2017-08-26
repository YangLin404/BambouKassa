package be.linyang.kassa.Model.items;

import org.mongodb.morphia.annotations.Entity;

@Entity("drinks")
public class Drink extends Item {

    public Drink()
    {

    }

    public Drink(String quicklink, String name, String ch_name, double price) {
        super(quicklink, name, ch_name, price);
    }
}
