package com.jesusandresbernallopez.project2;

class Reservation {

    private String flight;
    private int numSeats;
    private int reservationNumber;

    Reservation(int n, String f, int r) {
        this.flight = f;
        this.numSeats = n;
        this.reservationNumber = r;
    }

}
