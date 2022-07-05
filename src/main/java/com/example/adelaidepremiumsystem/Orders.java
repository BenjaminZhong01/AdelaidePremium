package com.example.adelaidepremiumsystem;

/** The type Orders. */
public class Orders {
  /** The Orderid. */
  private final int orderid;

  /** The Itemid. */
  private final int itemid;

  /** The Price. */
  private final double price;

  /** The Date. */
  private final String date;

  /**
   * Instantiates a new Orders.
   *
   * @param orderid the orderid
   * @param itemid the itemid
   * @param price the price
   * @param date the date
   */
  public Orders(int orderid, int itemid, double price, String date) {
        this.orderid = orderid;
        this.itemid = itemid;
        this.price = price;
        this.date = date;
    }

  /**
   * Gets orderid.
   *
   * @return the orderid
   */
  public int getOrderid() {
        return orderid;
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
   * Gets price.
   *
   * @return the price
   */
  public double getPrice() {
        return price;
    }

  /**
   * Gets date.
   *
   * @return the date
   */
  public String getDate() {
        return date;
    }
}
