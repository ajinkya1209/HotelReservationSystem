package com.project.springboothotelproject.enitites;

// Enum representing different types of rooms with associated prices
public enum RoomType {
    DELUXE(6500),            // Represents a deluxe room with a price of 5000
    SUITE(5400),           // Represents a suite room with a price of 10000
    DOUBLE_ROOM(2000),
    CLASSIC(4300),
    LUXURY(7300),
    SINGLE(1500),
    STUDIO(3300),
    VILLA(8900),
    PRESIDENTIAL_SUITE(20000);
    // Represents a double room with a price of 2000
    int price;                   // The price associated with each room type

    // Constructor to initialize the price for each room type
    RoomType(int price)
    {
        this.price=price;
    }

    // Getter method to retrieve the price of each room type
    public int getPrice() {
        return price;
    }
}
