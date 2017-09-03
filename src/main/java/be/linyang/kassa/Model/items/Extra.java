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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
