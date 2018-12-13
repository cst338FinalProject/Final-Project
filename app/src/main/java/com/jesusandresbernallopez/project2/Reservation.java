package com.jesusandresbernallopez.project2;

import java.util.ArrayList;

class Reservation {

    public Reservation() {
    }

    public boolean newReservation(Database db, String uname, String pass, int numSeats, String flightName) {
        String s = "SELECT * FROM customer WHERE username = " + uname;
        ArrayList<String> r = db.lookup(s);

        if (r.contains(uname) && r.contains(pass)) {
            //TODO: Create new reservation, return reservation id.
            s = "INSERT INTO reservations";
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteReservation(Database db, String uname, String pass, int reservationNumber) {
        String s = "DELETE FROM reservations WHERE id = " + reservationNumber;
        return true;
    }

    public ArrayList<String> searchReservations(Database db, String uname, String pass){
        String s = "SELECT * FROM reservations WHERE username = '" + uname + "';";
        return db.lookup(s);
    }
}
