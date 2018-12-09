package com.jesusandresbernallopez.project2;

public class Flight {

    public Flight() {
    }

    public void flightSearch(int tickets, String dLoc, String aLoc, Database db) {

        String s = "SELECT * FROM flights WHERE capacity = " + tickets + " AND departLoc = " + dLoc + " AND arriveLoc = " + aLoc + ";";

        db.lookup(s);
    }

    public boolean reserveSeat(int i) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
