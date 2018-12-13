package com.jesusandresbernallopez.project2;

import android.database.Cursor;

/**
 * "CREATE TABLE reservations(\n" +
 * "id          integer primary key autoincrement,\n" +
 * "seatsReq    integer not null," +
 * "flight_name varchar(20),\n" +
 * "customer_id integer,\n" +
 * "foreign key (flight_name) references flights (name) on delete cascade,\n" +
 * "foreign key (customer_id) references customers (id) on delete cascade);";
 **/

class Reservation {

    public Reservation() {
    }

    public boolean newReservation(Database db, String uname, String pass, int numSeats, String flightName) {
        Account a = new Account();
        if (a.verifyCust(uname, pass, db)) {

            int cID = a.getCustomerID(uname, db);
            String s = "INSERT INTO reservations (seatsReq, flight_name, customer_id) VALUES (" +
                    Integer.toString(numSeats) + ", ''" + flightName + "', " + Integer.toString(cID) + ");";

            db.insert(s);

            return true;

        } else {
            return false;
        }
    }

    public boolean deleteReservation(Database db, String uname, String pass, int reservationNumber) {
        String s = "DELETE FROM reservations WHERE id = " + reservationNumber;
        return true;
    }

    public Cursor searchReservations(Database db, String uname, String pass) {
        String s = "SELECT * FROM reservations WHERE username = '" + uname + "';";
        return db.lookup(s);
    }
}
