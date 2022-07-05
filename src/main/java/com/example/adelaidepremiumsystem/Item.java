package com.example.adelaidepremiumsystem;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * The type Item.
 */
public class Item {
    /**
     * The Itemid.
     */
private final int  itemid;
    /**
     * The Itemitem.
     */
private final String itemitem;
    /**
     * The Itemcolor.
     */
private final String itemcolor;
    /**
     * The Itemsize.
     */
private final String itemsize;
    /**
     * The Itemprice.
     */
private final double itemprice;
    /**
     * The Itemstock.
     */
private final int itemstock;
    /**
     * The Itemdescription.
     */
private final String itemdescription;


    /**
     * Instantiates a new Item.
     *
     * @param item_item the item item
     * @param item_color the item color
     * @param item_size the item size
     */
public Item(String item_item, String item_color,String item_size){
        this.itemitem = item_item;
        this.itemcolor = item_color;
        this.itemsize = item_size;
        this.itemid = 0;
        this.itemprice = 0;
        this.itemstock = 0;
        this.itemdescription = "";
    }

    /**
     * Instantiates a new Item.
     *
     * @param item_id the item id
     * @param item_item the item item
     * @param item_color the item color
     * @param item_size the item size
     * @param itemstock the itemstock
     * @param item_description the item description
     */
public Item(int item_id, String item_item, String item_color,
                String item_size, int itemstock, String item_description) {
        this.itemid = item_id;
        this.itemitem = item_item;
        this.itemcolor = item_color;
        this.itemsize = item_size;
        this.itemprice = 0;
        this.itemstock = itemstock;
        this.itemdescription = item_description;
    }

    /**
     * Instantiates a new Item.
     *
     * @param item_id the item id
     * @param item_item the item item
     * @param item_color the item color
     * @param item_size the item size
     * @param item_price the item price
     * @param itemstock the itemstock
     * @param item_description the item description
     */
public Item(int item_id, String item_item, String item_color,
                 String item_size, double item_price, int itemstock, String item_description) {
        this.itemid = item_id;
        this.itemitem = item_item;
        this.itemcolor = item_color;
        this.itemsize = item_size;
        this.itemprice = item_price;
        this.itemstock = itemstock;
        this.itemdescription = item_description;
    }


    /**
     * Gets itemid.
     *
     * @return the itemid
     */
public int getItemid() {
        return itemid;
    }

    /**
     * Gets itemitem.
     *
     * @return the itemitem
     */
public String getItemitem() {
        return itemitem;
    }

    /**
     * Get itemcolor string.
     *
     * @return the string
     */
public String getItemcolor(){
        return itemcolor;
    }

    /**
     * Get itemsize string.
     *
     * @return the string
     */
public String getItemsize(){
        return itemsize;
    }

    /**
     * Get itemprice double.
     *
     * @return the double
     */
public double getItemprice(){
        return itemprice;
    }

    /**
     * Get itemdescription string.
     *
     * @return the string
     */
public String getItemdescription(){
        return itemdescription;
    }

    /**
     * Get itemstock int.
     *
     * @return the int
     */
public int getItemstock(){return itemstock;}
}