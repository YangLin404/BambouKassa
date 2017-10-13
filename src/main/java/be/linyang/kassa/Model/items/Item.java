package be.linyang.kassa.Model.items;

import be.linyang.kassa.Model.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("item")
public class Item {
    @Id
    private ObjectId id;
	@JsonView(View.Summary.class)
    private String quicklink;
	@JsonView(View.Summary.class)
    private String name;
	@JsonView(View.Summary.class)
    private String ch_name;
	@JsonView(View.Summary.class)
    private double price;
	@JsonView(View.Summary.class)
    private double tax;
	@JsonView(View.Summary.class)
    private ItemType itemType;

    public Item()
    {

    }

    public Item(String quicklink, String name, String ch_name, double price, ItemType itemType) {
        this();
        this.quicklink = quicklink;
        this.name = name;
        this.price = price;
        this.ch_name = ch_name;
        this.itemType = itemType;
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

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }



    @Override
    public int hashCode() {
        return quicklink != null ? quicklink.hashCode() : 0;
    }
}
