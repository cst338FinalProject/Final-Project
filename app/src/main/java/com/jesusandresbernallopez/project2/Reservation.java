package com.jesusandresbernallopez.project2;

class Reservation {

    public Reservation() {

    }

    public boolean newReservation(Database db, String uname, String pass, int numSeats, String flightName) {
        String s = "SELECT * FROM customer WHERE uname = " + uname;
        String r = db.lookup(s);

        if (r.contains(uname) && r.contains(pass)) {
            //TODO: Create new reservation, return reservation id.
            s = "INSERT INTO reservation";
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteReservation(Database db, String uname, String pass, int reservationNumber) {
        String s = "DELETE FROM reservation WHERE id = " + reservationNumber;
        return true;
    }
}
