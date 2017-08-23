package be.linyang.kassa.Model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Item {
    @Id
    private ObjectId id;
    private String quicklink;
    private String name;
    private double price;
    private double tax;

    public Item(String quicklink, String name, double price) {
        this.quicklink = quicklink;
        this.name = name;
        this.price = price;
    }

    public ObjectId getId() {
        return id;
    }

    public String getQuicklink() {
        return quicklink;
    }

    public void setQuicklink(String quicklink) {
        this.quicklink = quicklink;
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
