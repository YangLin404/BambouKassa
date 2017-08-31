package be.linyang.kassa.Model.items;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("extra")
public class Extra {
    @Id
    private ObjectId id;
    private String name;
    private double price;

    public Extra() {}

    public Extra(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
