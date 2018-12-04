package com.jesusandresbernallopez.project2;

public class Flight {
    String name;
    int reservedSeats;
    int capacity;

    public Flight(String s, int c) {
        this.reservedSeats = 0;
        this.name = s;
        this.capacity = c;
    }

    public boolean reserveSeat(int i) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
