package be.linyang.kassa.Repository;

import be.linyang.kassa.Model.items.Extra;
import be.linyang.kassa.Model.items.Item;
import be.linyang.kassa.Model.items.ItemType;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Component
public class DataFactory {

    private List<Item> startItems;
    private List<Extra> startExtra;

    @PostConstruct
    private void init() {
        this.startItems = new LinkedList<>();
        this.startExtra = new LinkedList<>();
    }

    public List<Extra> initExtra() {
        addExtra();
        return this.startExtra;
    }

    public List<Item> initItems() {
        addSoup();
        addEntree();
        addNasi();
        addBami();
        addMihoen();
        addChopChoy();
        addChicken();
        addMeat();
        addChineseScampi();
        addOmelet();
        addGonBao();
        addDessert();
        addSpeciality();
        addVegi();
        addSupplements();
        addMenu();
        addApertive();
        addGlassWine();
        addChineseWine();
        addRedWine();
        addWhiteWine();
        addRoseWine();
        addTableWine();
        addColdDrink();
        addWarmDrink();
        addStrongDrink();
        return this.startItems;
    }

    private void addExtra() {
        startExtra.add(new Extra("Rijst", 0d));
        startExtra.add(new Extra("Friet", 0d));
        startExtra.add(new Extra("Nasi", 2d));
        startExtra.add(new Extra("Bami", 2d));
        startExtra.add(new Extra("Mihoen", 2d));
    }

    private void addStrongDrink() {
        startItems.add(new Item("Cognac","Cognac","",6d, ItemType.Drink));
        startItems.add(new Item("Grand Marnier","Grand Marnier","",5.5d, ItemType.Drink));
        startItems.add(new Item("Cointreau","Cointreau","",5.5d, ItemType.Drink));
        startItems.add(new Item("Amaretto","Amaretto","",5.5d, ItemType.Drink));
        startItems.add(new Item("Whisky","Whisky","",5.5d, ItemType.Drink));
        startItems.add(new Item("Gin","Gin","",5.5d, ItemType.Drink));
        startItems.add(new Item("Granjenever","Granjenever","",4.5d, ItemType.Drink));
        startItems.add(new Item("Mei Kwei Lu","Mei Kwei Lu","",5.5d, ItemType.Drink));
    }

    private void addWarmDrink() {
        startItems.add(new Item("Chinese thee","Chinese thee","",2.5d, ItemType.Drink));
        startItems.add(new Item("Engelse thee","Engelse thee","",2.5d, ItemType.Drink));
        startItems.add(new Item("Thee citroen","Thee citroen","",2.5d, ItemType.Drink));
        startItems.add(new Item("Koffie","Koffie","",2.5d, ItemType.Drink));
    }

    private void addColdDrink() {
        startItems.add(new Item("Bier","Bier","",2.5d, ItemType.Drink));
        startItems.add(new Item("Hoegaarden","Hoegaarden","",2.5d, ItemType.Drink));
        startItems.add(new Item("Alcoholvrij bier","Alcoholvrij bier","",2.3d, ItemType.Drink));
        startItems.add(new Item("Palm","Palm","",2.5d, ItemType.Drink));
        startItems.add(new Item("Duvel","Duvel","",4d, ItemType.Drink));
        startItems.add(new Item("Tsingtao","Tsingtao","",4d, ItemType.Drink));
        startItems.add(new Item("Tonic","Tonic","",2.5d, ItemType.Drink));
        startItems.add(new Item("Cola","Cola","",2.5d, ItemType.Drink));
        startItems.add(new Item("Cola light","Cola light","",2.5d, ItemType.Drink));
        startItems.add(new Item("Cola zero","Cola zero","",2.5d, ItemType.Drink));
        startItems.add(new Item("Fanta","Fanta","",2.5d, ItemType.Drink));
        startItems.add(new Item("Ice tea","Ice tea","",2.5d, ItemType.Drink));
        startItems.add(new Item("Spa plat","Spa plat","",2d, ItemType.Drink));
        startItems.add(new Item("Spa bruis","Spa bruis","",2d, ItemType.Drink));
        startItems.add(new Item("Fles spa plat","Fles spa plat","",6d, ItemType.Drink));
        startItems.add(new Item("Fles spa bruis","Fles spa bruis","",6d, ItemType.Drink));
        startItems.add(new Item("Appelsiensap","Appelsiensap","",2.3d, ItemType.Drink));
        startItems.add(new Item("Appelsap","Appelsap","",2.5d, ItemType.Drink));
    }

    private void addTableWine() {
        startItems.add(new Item("1/4 Rode wijn","1/4 Rode wijn","",5d, ItemType.Drink));
        startItems.add(new Item("1/2 Rode wijn","1/2 Rode wijn","",7.5d, ItemType.Drink));
        startItems.add(new Item("Fles Rode wijn","Fles Rode wijn","",13.5d, ItemType.Drink));
        startItems.add(new Item("1/4 Witte wijn","1/4 Witte wijn","",5d, ItemType.Drink));
        startItems.add(new Item("1/2 Witte wijn","1/2 Witte wijn","",7.5d, ItemType.Drink));
        startItems.add(new Item("Fles Witte wijn","Fles Witte wijn","",13.5d, ItemType.Drink));
        startItems.add(new Item("1/4 Rosé wijn","1/4 Rosé wijn","",5d, ItemType.Drink));
        startItems.add(new Item("1/2 Rosé wijn","1/2 Rosé wijn","",7.5d, ItemType.Drink));
        startItems.add(new Item("Fles Rosé wijn","Fles Rosé wijn","",13.5d, ItemType.Drink));
    }

    private void addRoseWine() {
        startItems.add(new Item("Cabernet d'Anjou","Cabernet d'Anjou","",15d, ItemType.Drink));
        startItems.add(new Item("Côtes de Provence","Côtes de Provence","",16d, ItemType.Drink));
        startItems.add(new Item("Rose de loire","Rose de loire","",15d, ItemType.Drink));
    }

    private void addWhiteWine() {
        startItems.add(new Item("Chablis","Chablis","",20d, ItemType.Drink));
        startItems.add(new Item("Chardonnay","Chardonnay","",17d, ItemType.Drink));
    }

    private void addRedWine() {
        startItems.add(new Item("Côtes du Rhône","Côtes du Rhône","",18d, ItemType.Drink));
        startItems.add(new Item("Montagne Saint-Emilion","Montagne Saint-Emilion","",19d, ItemType.Drink));
        startItems.add(new Item("Chateauneuf du Pape","Chateauneuf du Pape","",24d, ItemType.Drink));
        startItems.add(new Item("Haut-Médoc","Haut-Médoc","",25d, ItemType.Drink));
    }

    private void addChineseWine() {
        startItems.add(new Item("Fles Lychee wijn","Fles Lychee wijn","",16d, ItemType.Drink));
        startItems.add(new Item("Fles Chinese rode wijn","Fles Chinese rode wijn","",16d, ItemType.Drink));
        startItems.add(new Item("Fles Chinese witte wijn","Fles Chinese witte wijn","",16d, ItemType.Drink));
        startItems.add(new Item("Fles Kuei Hua Chen Chiew","Fles Kuei Hua Chen Chiew","",16d, ItemType.Drink));
    }

    private void addGlassWine() {
        startItems.add(new Item("Glas witte wijn","Glas witte wijn","",4d, ItemType.Drink));
        startItems.add(new Item("Glas rode wijn","Glas rode wijn","",4d, ItemType.Drink));
        startItems.add(new Item("Glas rosé wijn","Glas rosé wijn","",4d, ItemType.Drink));
        startItems.add(new Item("Glas lychee wijn","Glas lychee wijn","",5.5d, ItemType.Drink));
        startItems.add(new Item("Glas Chinese rode wijn","Glas Chinese rode wijn","",4.5d, ItemType.Drink));
        startItems.add(new Item("Glas Chinese witte wijn","Glas Chinese witte wijn","",4.5d, ItemType.Drink));
        startItems.add(new Item("Kuei Hua Chen Chiew","Kuei Hua Chen Chiew","",4.5d, ItemType.Drink));
    }

    private void addApertive() {
        startItems.add(new Item("Campari","Campari","",4.5d, ItemType.Drink));
        startItems.add(new Item("Campari soda","Campari soda","",5d, ItemType.Drink));
        startItems.add(new Item("Campari orange","Campari orange","",5d, ItemType.Drink));
        startItems.add(new Item("Martini wit","Martini wit","",3.5d, ItemType.Drink));
        startItems.add(new Item("Martini rood","Martini rood","",3.5d, ItemType.Drink));
        startItems.add(new Item("Porto wit","Porto wit","",3.5d, ItemType.Drink));
        startItems.add(new Item("Porto rood","Porto rood","",3.5d, ItemType.Drink));
        startItems.add(new Item("Sherry","Sherry","",3.5d, ItemType.Drink));
        startItems.add(new Item("Gancia","Gancia","",3.5d, ItemType.Drink));
        startItems.add(new Item("Gin Tonic","Gin Tonic","",6.5d, ItemType.Drink));
        startItems.add(new Item("Lychee wijn","Lychee wijn","",3.5d, ItemType.Drink));
        startItems.add(new Item("Kuei Hua Chen Chiew","Kuei Hua Chen Chiew","",3.5d, ItemType.Drink));
    }

    private void addMenu() {
        startItems.add(new Item("r1","Chinese rijsttafel A","",20d, ItemType.Menu));
        startItems.add(new Item("r2","Chinese rijsttafel B","",25d, ItemType.Menu));
        startItems.add(new Item("NY2","Nieuwjaar menu 2p","",30d, ItemType.Menu));
        startItems.add(new Item("NY4","Nieuwjaar menu 4p","",38d, ItemType.Menu));
        startItems.add(new Item("NY2K","Nieuwjaar menu 2p korting","",27d, ItemType.Menu));
        startItems.add(new Item("NY4K","Nieuwjaar menu 4p korting","",34.2d, ItemType.Menu));
    }

    private void addSupplements() {
        startItems.add(new Item("106","Currysaus","",2.5d, ItemType.Supplement));
        startItems.add(new Item("107","Zoetzure saus","",2.5d, ItemType.Supplement));
        startItems.add(new Item("108","Witte rijst","",2.5d, ItemType.Supplement));
        startItems.add(new Item("109","Frieten","",2.5d, ItemType.Supplement));
        startItems.add(new Item("110","Gebakken rijst bij gerecht","",2d, ItemType.Supplement));
        startItems.add(new Item("111","Gebakken rijst","",3d, ItemType.Supplement));
        startItems.add(new Item("112","Bamigoring","",3.5d, ItemType.Supplement));
        startItems.add(new Item("113","Mihoen","",3.5d, ItemType.Supplement));
        startItems.add(new Item("114","Ketchup","",1.5d, ItemType.Supplement));
        startItems.add(new Item("115","Mayonaise","",1.5d, ItemType.Supplement));
        startItems.add(new Item("116","supplement 1.0","", 1d, ItemType.Supplement));
        startItems.add(new Item("117","supplement 1.2","", 1.2d, ItemType.Supplement));
        startItems.add(new Item("118","supplement 1.4","", 1.4d, ItemType.Supplement));
        startItems.add(new Item("119","supplement 1.6","", 1.6d, ItemType.Supplement));
        startItems.add(new Item("120","supplement 1.8","", 1.8d, ItemType.Supplement));
        startItems.add(new Item("121","supplement 2.0","", 2.0d, ItemType.Supplement));
    }

    private void addVegi() {
        startItems.add(new Item("100","Nasi natuur","",8.5d, ItemType.Nasi));
        startItems.add(new Item("101","Bami natuur","",8.5d, ItemType.Bami));
        startItems.add(new Item("102","Mihoen natuur","",8.5d, ItemType.Supplement));
        startItems.add(new Item("103","Chop-choy natuur","",8.5d, ItemType.Supplement));
        startItems.add(new Item("104","Diverse groenten","",8.5d, ItemType.Supplement));
        startItems.add(new Item("105","Omelet natuur","",8.5d, ItemType.Supplement));
    }

    private void addSpeciality() {
        startItems.add(new Item("86","Kippenvleugels met look","",10.0d, ItemType.MainDishe));
        startItems.add(new Item("87","Gefrituurde kippenvleugels","",10.0d, ItemType.MainDishe));
        startItems.add(new Item("88","Kippenvleugels met peper en zout","",10.0d, ItemType.MainDishe));
        startItems.add(new Item("89","Chinese ribbetjes met look","",11.0d, ItemType.MainDishe));
        startItems.add(new Item("90","Chinese ribbetjes met peper en zout","",11.0d, ItemType.MainDishe));
        startItems.add(new Item("91","Kikkerbilletjes met look","",12.0d, ItemType.MainDishe));
        startItems.add(new Item("92","Chinese garnalen met look","",14.0d, ItemType.MainDishe));
        startItems.add(new Item("93","Inktvis in currysaus","",12.0d, ItemType.MainDishe));
        startItems.add(new Item("94","Inktvis in tausi","",12.5d, ItemType.MainDishe));
        startItems.add(new Item("95","Inkvis in pikante saus","",12.5d, ItemType.MainDishe));
        startItems.add(new Item("96","Kippenlevers met groenten","",9.5d, ItemType.MainDishe));
        startItems.add(new Item("97","Lamskotelet met look","",16.0d, ItemType.MainDishe));
        startItems.add(new Item("98","Lamskotelet met ajuin en champignon","",16.0d, ItemType.MainDishe));
        startItems.add(new Item("99","Pangasfilet in zoetzure saus","",16.0d, ItemType.MainDishe));
    }

    private void addDessert() {
        startItems.add(new Item("d1","Vanille ijs","",3.0d, ItemType.Dessert));
        startItems.add(new Item("d2","Dame blanche","",3.5d, ItemType.Dessert));
        startItems.add(new Item("d3","Lychees fruit","",3.5d, ItemType.Dessert));
        startItems.add(new Item("d4","Ananas","",3.5d, ItemType.Dessert));
        startItems.add(new Item("d5","Gebakken ananas","",4.0d, ItemType.Dessert));
        startItems.add(new Item("d6","Gebakken banaan","",4.0d, ItemType.Dessert));
        startItems.add(new Item("d7","Mochi aardbei","",4.0d, ItemType.Dessert));
        startItems.add(new Item("d8","Mochi mango","",4.0d, ItemType.Dessert));
        startItems.add(new Item("d9","Mochi mucha","",4.0d, ItemType.Dessert));
        startItems.add(new Item("d10","Mochi choco","",5.0d, ItemType.Dessert));
        startItems.add(new Item("d11","Mochi mix","",6.0d, ItemType.Dessert));
    }

    private void addOmelet() {
        startItems.add(new Item("82","Omelet met kip","",10.0d, ItemType.MainDishe));
        startItems.add(new Item("83","Omelet met geroosterd varkensvlees","",10.00d, ItemType.MainDishe));
        startItems.add(new Item("84","Omelet met Chinese garnalen","",14.5d, ItemType.MainDishe));
        startItems.add(new Item("85","Omelet met krab","",14.5d, ItemType.MainDishe));
    }

    private void addGonBao() {
        startItems.add(new Item("76","Eend in Gon Bao saus","",14.5d, ItemType.MainDishe));
        startItems.add(new Item("77","Kippenblokjes in Gon Bao saus","",12.0d, ItemType.MainDishe));
        startItems.add(new Item("78","Chinese garnalen in Gon Bao saus","",15.3d, ItemType.MainDishe));
        startItems.add(new Item("79","Rundvlees in Gon Bao saus","",13.0d, ItemType.MainDishe));
        startItems.add(new Item("80","Varkensvlees in Gon Bao saus","",12.5d, ItemType.MainDishe));
        startItems.add(new Item("81","Geroosterd varkensvlees in Gon Bao saus","",13.5d, ItemType.MainDishe));
    }

    private void addChineseScampi() {
        startItems.add(new Item("70","Chinese garnalen met groenten","",14.0d, ItemType.MainDishe));
        startItems.add(new Item("71","Chinese garnalen in tomatensaus","",14.5d, ItemType.MainDishe));
        startItems.add(new Item("72","Chinese garnalen in zoetzure saus","",14.5d, ItemType.MainDishe));
        startItems.add(new Item("73","Gebakken Chinese garnalen met zoetzure saus","",14.5d, ItemType.MainDishe));
        startItems.add(new Item("74","Chinese garnalen in currysaus","",14.5d, ItemType.MainDishe));
        startItems.add(new Item("75","Chinese garnalen in oestersaus","",14.5d, ItemType.MainDishe));
    }

    private void addMeat() {
        startItems.add(new Item("59","Babi pangang","",13.0d, ItemType.MainDishe));
        startItems.add(new Item("60","Geroosterd varkensvlees met groenten","",12.0d, ItemType.MainDishe));
        startItems.add(new Item("61","Gebakken varkensblokjes met zoetzure saus","",9.5d, ItemType.MainDishe));
        startItems.add(new Item("62","Varkensvlees met groenten","",9.5d, ItemType.MainDishe));
        startItems.add(new Item("63","Rundvlees met groenten","",10.0d, ItemType.MainDishe));
        startItems.add(new Item("64","Rundvlees in currysaus","",10.0d, ItemType.MainDishe));
        startItems.add(new Item("65","Rundvlees in tomatensaus","",10.0d, ItemType.MainDishe));
        startItems.add(new Item("66","Gebraden eend met groenten in pikante saus","",14.5d, ItemType.MainDishe));
        startItems.add(new Item("67","Gebraden eend met groenten in zoetzure saus","",14.5d, ItemType.MainDishe));
        startItems.add(new Item("68","Eend in sinaasappelsaus","",14.5d, ItemType.MainDishe));
        startItems.add(new Item("69","Peking eend voor 2 personen","",25d, ItemType.MainDishe));
    }

    private void addChicken() {
        startItems.add(new Item("43","Gebraden halve kip met curry","",11.0d, ItemType.MainDishe));
        startItems.add(new Item("44","Gebraden halve kip met tomatensaus","",11.0d, ItemType.MainDishe));
        startItems.add(new Item("45","Gebraden kip met groenten","",9.5d, ItemType.MainDishe));
        startItems.add(new Item("46","Gebraden kip met Chinese champignons","",9.5d, ItemType.MainDishe));
        startItems.add(new Item("47","Kippenblokjes met tausi","",9.0d, ItemType.MainDishe));
        startItems.add(new Item("48","Kippenblokjes met bamboe en groenten","",9.5d, ItemType.MainDishe));
        startItems.add(new Item("49","Kippenblokjes met Chinese champignons","",9.5d, ItemType.MainDishe));
        startItems.add(new Item("50","Kippenblokjes met champignons","",9.0d, ItemType.MainDishe));
        startItems.add(new Item("51","Kippenblokjes met ananas","",9.5d, ItemType.MainDishe));
        startItems.add(new Item("52","Kippenblokjes met verse ananas","",12.0d, ItemType.MainDishe));
        startItems.add(new Item("53","Kippenblokjes in tomatensaus","",9.0d, ItemType.MainDishe));
        startItems.add(new Item("54","Kippenblokjes in oestersaus","",9.0d, ItemType.MainDishe));
        startItems.add(new Item("55","Gebakken kippenblokjes met zoetzure saus","",9.5d, ItemType.MainDishe));
        startItems.add(new Item("56","Kip op drie wijzen bereid voor 2 personen","",25d, ItemType.MainDishe));
        startItems.add(new Item("57","Kippenblokjes in pikante saus","",9.0d, ItemType.MainDishe));
        startItems.add(new Item("58","Kippenblokjes in currysaus","",9.0d, ItemType.MainDishe));
    }

    private void addChopChoy() {
        startItems.add(new Item("37","Chop-choy met kip","",9d, ItemType.MainDishe));
        startItems.add(new Item("38","Chop-choy met varkensvlees","",9.5d, ItemType.MainDishe));
        startItems.add(new Item("39","Chop-choy met Chinese garnalen","",14.5d, ItemType.MainDishe));
        startItems.add(new Item("40","Chop-choy met kippenlevers","",9.5d, ItemType.MainDishe));
        startItems.add(new Item("41","Chop-choy met geroosterd varkensvlees","",10.0d, ItemType.MainDishe));
        startItems.add(new Item("42","Chop-choy speciaal op Chinese wijze","",11.0d, ItemType.MainDishe));
    }

    private void addBami() {
        startItems.add(new Item("28","Bami met kip","",9.0d, ItemType.Bami));
        startItems.add(new Item("29","Bami met varkensvlees","",9.5d, ItemType.Bami));
        startItems.add(new Item("30","Bami met Chinese garnalen","",14.5d, ItemType.Bami));
        startItems.add(new Item("31","Bami speciaal op Chinese wijze","",11.0d, ItemType.Bami));
    }

    private void addNasi() {
        startItems.add(new Item("23","Nasi met kip","",9d, ItemType.Nasi));
        startItems.add(new Item("24","Nasi met varkensvlees","",9.5d, ItemType.Nasi));
        startItems.add(new Item("25","Nasi met krab","",14.5d, ItemType.Nasi));
        startItems.add(new Item("26","Nasi met Chinese garnalen","",14.5d, ItemType.Nasi));
        startItems.add(new Item("27","Nasi speciaal op Chinese wijze","",11d, ItemType.Nasi));
    }

    private void addMihoen() {
        startItems.add(new Item("32","Mihoen met kip","",10.0d, ItemType.Nasi));
        startItems.add(new Item("33","Mihoen met varkensvlees","",10.5d, ItemType.Nasi));
        startItems.add(new Item("34","Mihoen met Chinese garnalen","",14.5d, ItemType.Nasi));
        startItems.add(new Item("35","Mihoen met speciaal op Chinese wijze","",12.0d, ItemType.Nasi));
        startItems.add(new Item("36","Singapore mihoen","",13.0d, ItemType.Nasi));

    }

    private void addEntree() {
        startItems.add(new Item("11","Loempia met kip","",4.0d, ItemType.Entree));
        startItems.add(new Item("12","Mini loempia's","",4.0d, ItemType.Entree));
        startItems.add(new Item("13","Loempia met geroosterd varkensvlees","",4.5d, ItemType.Entree));
        startItems.add(new Item("14","Loempia met krab","",5.0d, ItemType.Entree));
        startItems.add(new Item("15","Speciaal voorgerecht","",7.0d, ItemType.Entree));
        startItems.add(new Item("16","Vleessatés","",4.5d, ItemType.Entree));
        startItems.add(new Item("17","Kipsatés","",4.5d, ItemType.Entree));
        startItems.add(new Item("18","Kroepoek","",2.5d, ItemType.Entree));
        startItems.add(new Item("19","Dim Sum mix","",7.0d, ItemType.Entree));
        startItems.add(new Item("20","Dim Sum garnalen","",5.0d, ItemType.Entree));
        startItems.add(new Item("21","Dim Sum varkensvlees","",4.5d, ItemType.Entree));
        startItems.add(new Item("22","Ka li kok","",4.0d, ItemType.Entree));
    }

    private void addSoup() {
        startItems.add(new Item("1","Kippensoep met Chinese champignons","",3.0d, ItemType.Soup));
        startItems.add(new Item("2","Kippensoep met champignons","",3.0d, ItemType.Soup));
        startItems.add(new Item("3","Kippensoep met asperges","",3.0d, ItemType.Soup));
        startItems.add(new Item("4","Kippensoep met bamboe","",3.0d, ItemType.Soup));
        startItems.add(new Item("5","Maissoep","",4.0d, ItemType.Soup));
        startItems.add(new Item("6","Wantan soep","",4.5d, ItemType.Soup));
        startItems.add(new Item("7","Kippenleversoep","",3.5d, ItemType.Soup));
        startItems.add(new Item("8","Tomatensoep","",3.0d, ItemType.Soup));
        startItems.add(new Item("9","Haaienvinnensoep","",4.0d, ItemType.Soup));
        startItems.add(new Item("10","Pikante soep","",3.5d, ItemType.Soup));
    }
}
