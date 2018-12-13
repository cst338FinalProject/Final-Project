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

        return db.delete(s);
    }

    public Cursor getReservations(Database db, String uname, String pass) {

        Account a = new Account();
        int cid = a.getCustomerID(uname, db);
        String s = "SELECT * FROM reservations WHERE customer_id = " + cid + ";";

        return db.lookup(s);
    }

}
