package com.example.coffeeappv3;

public class Order {
    //Attributes for the order
    private int _id;
    private String _custName;
    private int _saleAmount;

    //default Constructor
    public Order(){
        _id = 0;
        _custName = null;
        _saleAmount = 0;
    }

    //Second Constructor
    public Order(String custName, int saleAmount) {
        _custName = custName;
        _saleAmount = saleAmount;
    }

    //get methods
    public int get_id() {return _id;}
    public String get_custName() {return _custName;}
    public double get_saleAmount() {return _saleAmount;}

    //set methods
    public void set_id(int id) {_id = id;}
    public void set_custName(String custName) {_custName = custName;}
    public void set_saleAmount(int saleAmount) {_saleAmount = saleAmount;}
}
