package com.jesusandresbernallopez.project2;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

/**
 * "CREATE TABLE flights (\n" +
 * "name         varchar(20) not null unique,\n" +
 * "departLoc    varchar(20) not null,\n" +
 * "destinLoc    varchar(20) not null,\n" +
 * "departTime   integer,\n" +
 * "flightCap    integer not null,\n" +
 * "price        decimal not null,\n" +
 * "claimedSeats integer not null,\n" +
 * "primary key (name));";
 **/
public class Flight {

    public Flight() {
    }

    public ArrayList<String> flightSearch(int tickets, String dLoc, String aLoc, Database db) {

        String s = "SELECT * FROM flights WHERE flightCap > " + tickets + " AND departLoc = '" + dLoc + "' AND destinLoc = '" + aLoc + "';";

        try {
            if (db.lookup(s) == null) {
                throw new Exception("fuck this");
            }
           
            Cursor c = db.lookup(s);
            ArrayList<String> list = new ArrayList<>();

            StringBuilder sb = new StringBuilder();
            int col = c.getColumnCount();
            int row = c.getCount();

            for (int i = 0; i < row; i++) {
                c.moveToNext();
                for (int j = 0; j < col; j++) {
                    try {
                        sb.append(c.getString(j) + ",");
                    } catch (Exception e) {
                        sb.append(Integer.toString(c.getInt(j)) + ",");
                    }
                }
                list.add(sb.toString());
                sb = new StringBuilder();
            }
            c.close();
            db.close();
            return list;

        } catch (Exception e) {
            Log.d("Exp", e.getLocalizedMessage());
            System.exit(69);
            return null;
        }
    }

    public boolean reserveSeat(int i) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean addFlight(String name, String dep, String arriv, int time, int cap, Float price, Database db){

        String s = "INSERT INTO flights (name, departLoc, destinLoc, departTime, flightCap, price, claimedSeats)" +
                " VALUES ('"+ name + "', '" + dep +"', '"+ arriv +"', " + time + ", " + cap + ", " + price + ", 0);";

        return db.insert(s);
    }
}
