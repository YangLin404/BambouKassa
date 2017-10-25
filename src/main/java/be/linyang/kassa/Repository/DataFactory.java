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
        addChopChoy();
        addChicken();
        addMeat();
        addChineseScampi();
        addEuroSchotels();
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
        startExtra.add(new Extra("Nasi", 1.2d));
        startExtra.add(new Extra("Bami", 1.2d));
        startExtra.add(new Extra("Mihoen", 1.2d));
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
        startItems.add(new Item("/2 Rosé wijn","1/2 Rosé wijn","",7.5d, ItemType.Drink));
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
        startItems.add(new Item("r1","R1","",34.8d, ItemType.Menu));
        startItems.add(new Item("r2","R2","",90d, ItemType.Menu));
    }

    private void addSupplements() {
        startItems.add(new Item("s1","Currysaus","",2d, ItemType.Supplement));
        startItems.add(new Item("s2","Tomatensaus","",2d, ItemType.Supplement));
        startItems.add(new Item("s3","Witte rijst","",2.2d, ItemType.Supplement));
        startItems.add(new Item("s4","Frieten","",2.5d, ItemType.Supplement));
        startItems.add(new Item("s5","Gebakken rijst","",2.5d, ItemType.Supplement));
        startItems.add(new Item("s6","Bami","",3.2d, ItemType.Supplement));
        startItems.add(new Item("s7","Mihoen","",3.7d, ItemType.Supplement));
        startItems.add(new Item("s8","Zoetzure saus natuur","",2d, ItemType.Supplement));
        startItems.add(new Item("s9","Zoetzure saus met fruit","",2.4d, ItemType.Supplement));
        startItems.add(new Item("s10","Satésaus","",2.4d, ItemType.Supplement));
        startItems.add(new Item("s11","Pikante saus","",2.3d, ItemType.Supplement));
        startItems.add(new Item("s12","Oestersaus","",2.3d, ItemType.Supplement));
        startItems.add(new Item("s13","Ketchup","",1.4d, ItemType.Supplement));
        startItems.add(new Item("s14","Mayonaise","",1.4d, ItemType.Supplement));
    }

    private void addVegi() {
        startItems.add(new Item("N1","Nasi natuur","",5.5d, ItemType.Nasi));
        startItems.add(new Item("N2","Bami natuur","",5.5d, ItemType.Bami));
        startItems.add(new Item("N3","Chop-choy natuur","",5.1d, ItemType.MainDishe));
        startItems.add(new Item("N4","Diverse groenten","",5.6d, ItemType.MainDishe));
        startItems.add(new Item("N5","Salade natuur","",3.4d, ItemType.MainDishe));
        startItems.add(new Item("N6","Omelet natuur","",4.8d, ItemType.MainDishe));
    }

    private void addSpeciality() {
        startItems.add(new Item("101","Gebraden kip in Chinese speciale saus","",9.2d, ItemType.MainDishe));
        startItems.add(new Item("102","Kippenvleugels met look","",9.1d, ItemType.MainDishe));
        startItems.add(new Item("103","kip curry speciaal","",9.2d, ItemType.MainDishe));
        startItems.add(new Item("104","Chinese ribbertjes met look","",10.6d, ItemType.MainDishe));
        startItems.add(new Item("105","Kikkerbilletjes met look","",10.6d, ItemType.MainDishe));
        startItems.add(new Item("106","Kikkerbilletjes met kip in oestersaus","",10.4d, ItemType.MainDishe));
        startItems.add(new Item("107","Chop-choy speciaal op Chinese wijze","",10.4d, ItemType.MainDishe));
        startItems.add(new Item("108","Inktvis in currysaus","",9.6d, ItemType.MainDishe));
        startItems.add(new Item("110","Gehele Chinese garnalen in tomatensaus","",13.8d, ItemType.MainDishe));
        startItems.add(new Item("111","Chinese garnalen met kip in oestersaus","",13.8d, ItemType.MainDishe));
        startItems.add(new Item("112","Gehele Chinese garnalen met champignons","",13.8d, ItemType.MainDishe));
        startItems.add(new Item("113","Gebakken Chinese garnalen met krabsaus","",14.4d, ItemType.MainDishe));
        startItems.add(new Item("114","Gebraden eend met groenten","",13.5d, ItemType.MainDishe));
        startItems.add(new Item("115","Kippenlevers met groenten","",8.8d, ItemType.MainDishe));
        startItems.add(new Item("116","Babi pangang","",11.7d, ItemType.MainDishe));
    }

    private void addDessert() {
        startItems.add(new Item("72","Lychees","",2.8d, ItemType.Dessert));
        startItems.add(new Item("73","Ananas","",2.3d, ItemType.Dessert));
        startItems.add(new Item("74","Vanille ijs","",2.3d, ItemType.Dessert));
        startItems.add(new Item("75","Dame blanche","",3.4d, ItemType.Dessert));
        startItems.add(new Item("76","Gebakken ananas","",3.1d, ItemType.Dessert));
    }

    private void addEuroSchotels() {
        startItems.add(new Item("65","Kippensteak in saus","",9.1d, ItemType.MainDishe));
        startItems.add(new Item("66","Omelet met ham","",8.1d, ItemType.MainDishe));
        startItems.add(new Item("67","Omelet met geroosterd varkensvlees","",9.2d, ItemType.MainDishe));
        startItems.add(new Item("68","Omelet met Chinese garnalen","",13.5d, ItemType.MainDishe));
        startItems.add(new Item("69","Omelet met krab","",14d, ItemType.MainDishe));
        startItems.add(new Item("70","Salade met krab","",13.8d, ItemType.MainDishe));
        startItems.add(new Item("71","Omelet met kip","",7.4d, ItemType.MainDishe));
    }

    private void addChineseScampi() {
        startItems.add(new Item("59","Chinese garnalen met groenten","",13.3d, ItemType.MainDishe));
        startItems.add(new Item("60","Chinese garnalen in tomatensaus","",13.3d, ItemType.MainDishe));
        startItems.add(new Item("61","Chinese garnalen in zoetzure saus","",13.3d, ItemType.MainDishe));
        startItems.add(new Item("62","Gebakken Chinese garnalen met zoetzure saus","",13.3d, ItemType.MainDishe));
        startItems.add(new Item("63","Chinese garnalen in currysaus","",13.3d, ItemType.MainDishe));
        startItems.add(new Item("64","Chinese garnalen in oestersaus","",13.3d, ItemType.MainDishe));
    }

    private void addMeat() {
        startItems.add(new Item("53","Geroosterd varkensvlees met groenten","",11.7d, ItemType.MainDishe));
        startItems.add(new Item("54","Gebakken varkensblokjes met zoetzure saus","",8.8d, ItemType.MainDishe));
        startItems.add(new Item("55","Varkensvlees met groenten","",8.8d, ItemType.MainDishe));
        startItems.add(new Item("56","Rundvlees met groenten","",9.6d, ItemType.MainDishe));
        startItems.add(new Item("57","Diverse vleessoorten met groenten","",10.2d, ItemType.MainDishe));
        startItems.add(new Item("58","Rundvlees in curry- of tomatensaus","",9.7d, ItemType.MainDishe));
    }

    private void addChicken() {
        startItems.add(new Item("36","Gebraden kwart kip met curry","",5.7d, ItemType.MainDishe));
        startItems.add(new Item("36a","Gebraden halve kip met curry","",8d, ItemType.MainDishe));
        startItems.add(new Item("37","Gebraden kwart kip met tomatensaus","",5.7d, ItemType.MainDishe));
        startItems.add(new Item("37a","Gebraden halve kip met tomatensaus","",8d, ItemType.MainDishe));
        startItems.add(new Item("38","Gebraden kip met groeten","",9.2d, ItemType.MainDishe));
        startItems.add(new Item("39","Gebraden kip met Chinese champignons","",9.4d, ItemType.MainDishe));
        startItems.add(new Item("40","Kippenblokjes met tausi","",8.9d, ItemType.MainDishe));
        startItems.add(new Item("41","Kippenblokjes met bamboe en groenten","",8.6d, ItemType.MainDishe));
        startItems.add(new Item("42","Kippenblokjes met Chinese champignons","",9d, ItemType.MainDishe));
        startItems.add(new Item("43","Kippenblokjes met champignons","",8.8d, ItemType.MainDishe));
        startItems.add(new Item("44","Kippenblokjes met ananas","",8.9d, ItemType.MainDishe));
        startItems.add(new Item("45","Kipfilet met ham","",9.2d, ItemType.MainDishe));
        startItems.add(new Item("46","Kippenblokjes met doperwten","",8.8d, ItemType.MainDishe));
        startItems.add(new Item("47","Kippenblokjes in tomatensaus","",8.9d, ItemType.MainDishe));
        startItems.add(new Item("48","Kippenblokjes in oestersaus","",8.8d, ItemType.MainDishe));
        startItems.add(new Item("49","Gebakken kippenblokjes met zoetzure saus","",8.7d, ItemType.MainDishe));
        startItems.add(new Item("50","Kip op drie wijzen bereid voor 2 personen","",22d, ItemType.MainDishe));
        startItems.add(new Item("51","Kippenblokjes in pikante saus","",8.9d, ItemType.MainDishe));
        startItems.add(new Item("52","Kippenblokjes in currysaus","",8.9d, ItemType.MainDishe));
    }

    private void addChopChoy() {
        startItems.add(new Item("30","Chop-choy met Chinese garnalen","",13.3d, ItemType.MainDishe));
        startItems.add(new Item("31","Chop-choy met Kip","",8d, ItemType.MainDishe));
        startItems.add(new Item("32","Chop-choy met varkensvlees","",8.2d, ItemType.MainDishe));
        startItems.add(new Item("33","Chop-choy met kippenlevers","",8.2d, ItemType.MainDishe));
        startItems.add(new Item("34","Chop-choy speciaal","",9.2d, ItemType.MainDishe));
        startItems.add(new Item("35","Chop-choy met geroosterd varkensvlees","",9.2d, ItemType.MainDishe));
    }

    private void addBami() {
        startItems.add(new Item("25","Bami met kip","",8.2d, ItemType.Bami));
        startItems.add(new Item("26","Bami met varkensvlees","",8.4d, ItemType.Bami));
        startItems.add(new Item("27","Bami met Chinese garnalen","",14.3d, ItemType.Bami));
        startItems.add(new Item("28","Bami speciaal","",9.4d, ItemType.Bami));
        startItems.add(new Item("29","Bami speciaal op Chinese wijze","",10.2d, ItemType.Bami));
    }

    private void addNasi() {
        startItems.add(new Item("19","Nasi met kip","",8d, ItemType.Nasi));
        startItems.add(new Item("20","Nasi met varkensvlees","",8.2d, ItemType.Nasi));
        startItems.add(new Item("21","Nasi met krab","",13.3d, ItemType.Nasi));
        startItems.add(new Item("22","Nasi met Chinese garnalen","",13.3d, ItemType.Nasi));
        startItems.add(new Item("23","Nasi speciaal","",9.2d, ItemType.Nasi));
        startItems.add(new Item("24","Nasi speciaal op Chinese wijze","",10d, ItemType.Nasi));
    }

    private void addEntree() {
        startItems.add(new Item("11a","Loempia met kip","",3.1d, ItemType.Entree));
        startItems.add(new Item("11b","Mini loempia's","",3.1d, ItemType.Entree));
        startItems.add(new Item("12","Loempia met geroosterd varkensvlees","",3.3d, ItemType.Entree));
        startItems.add(new Item("13","Loempia met krab","",3.8d, ItemType.Entree));
        startItems.add(new Item("14","Speciaal voorgerecht","",6.5d, ItemType.Entree));
        startItems.add(new Item("15","Vleessatés","",4.5d, ItemType.Entree));
        startItems.add(new Item("16","Kipsatés","",4.5d, ItemType.Entree));
        startItems.add(new Item("17","Kroepoek","",2.4d, ItemType.Entree));
        startItems.add(new Item("18","Chinees voorgerecht","",5.7d, ItemType.Entree));
    }

    private void addSoup() {
        startItems.add(new Item("1","Kippensoep met Chinese champignons","",2.8d, ItemType.Soup));
        startItems.add(new Item("2","Kippensoep met champignons","",2.7d, ItemType.Soup));
        startItems.add(new Item("3","Kippensoep met asperges","",2.7d, ItemType.Soup));
        startItems.add(new Item("4","Kippensoep met bamboe","",2.7d, ItemType.Soup));
        startItems.add(new Item("5","Wantan soep","",4.1d, ItemType.Soup));
        startItems.add(new Item("6","Kippenleversoep","",2.8d, ItemType.Soup));
        startItems.add(new Item("7","Tomatensoep","",2.8d, ItemType.Soup));
        startItems.add(new Item("8","Varkensvleessoep","",3.1d, ItemType.Soup));
        startItems.add(new Item("9","Haaienvinnensoep","",3.2d, ItemType.Soup));
        startItems.add(new Item("10","Pikante soep","",2.9d, ItemType.Soup));
    }

}
