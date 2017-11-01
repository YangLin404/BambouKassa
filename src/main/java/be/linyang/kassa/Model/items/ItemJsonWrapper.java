package be.linyang.kassa.Model.items;

import be.linyang.kassa.Model.View;

public class ItemJsonWrapper {

    private String quicklink;
    private String name;
    private String ch_name;
    private double price;
    private ItemType itemType;

    public ItemJsonWrapper() { }

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

    public String getCh_name() {
        return ch_name;
    }

    public void setCh_name(String ch_name) {
        this.ch_name = ch_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
