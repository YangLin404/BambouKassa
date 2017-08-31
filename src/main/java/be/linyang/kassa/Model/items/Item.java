package be.linyang.kassa.Model.items;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.util.LinkedList;
import java.util.List;

@Entity("item")
public class Item {
    @Id
    private ObjectId id;
    private String quicklink;
    private String name;
    private String ch_name;
    private double price;
    private double tax;
    private Type type;

    @Reference
    private List<Extra> extras;

    public Item()
    {
        this.extras = new LinkedList<>();
    }

    public Item(String quicklink, String name, String ch_name, double price, Type type) {
        this();
        this.quicklink = quicklink;
        this.name = name;
        this.price = price;
        this.ch_name = ch_name;
        this.type = type;
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

    public String getCh_name() {
        return ch_name;
    }

    public void setCh_name(String ch_name) {
        this.ch_name = ch_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return quicklink != null ? quicklink.equals(item.quicklink) : item.quicklink == null;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }

    public void addExtra(Extra extra) {
        this.extras.add(extra);
    }

    @Override
    public int hashCode() {
        return quicklink != null ? quicklink.hashCode() : 0;
    }
}
