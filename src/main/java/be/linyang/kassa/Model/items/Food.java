package be.linyang.kassa.Model.items;


import org.mongodb.morphia.annotations.Entity;

@Entity("foods")
public class Food extends Item {

    public Food()
    {

    }

    public Food(String quicklink, String name, String ch_name, double price) {
        super(quicklink, name, ch_name, price);
    }


}
