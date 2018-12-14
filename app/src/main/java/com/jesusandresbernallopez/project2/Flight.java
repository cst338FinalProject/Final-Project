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

        String s = "SELECT * FROM flights WHERE (flightCap - claimedSeats - " + tickets + ") > 0 AND departLoc = '" + dLoc + "' AND destinLoc = '" + aLoc + "';";
        Log.d("size", "starting query search" + s);
        try {
            if (db.lookup(s) == null) {
                Log.d("size", "null lookup");
                throw new Exception("fuck this");
            }
           
            Cursor c = db.lookup(s);
            ArrayList<String> list = new ArrayList<>();

            StringBuilder sb = new StringBuilder();
            Log.d("size", "Starting cursor");
            int col = c.getColumnCount();
            int row = 0;
            try{
                row = c.getCount();
            }catch(Exception e){
                Log.d("blah", "Message: " + e.getLocalizedMessage());
            }

            Log.d("size", "cursor values put into variables");
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
            Log.d("size", "about to return list");
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

    public boolean addFlight(String name, String dep, String arriv, int hour, int min, int cap, Float price, Database db){

        String s = "INSERT INTO flights (name, departLoc, destinLoc, departHour, departMin, flightCap, price, claimedSeats)" +
                " VALUES ('"+ name + "', '" + dep +"', '"+ arriv + "', " + hour + ", " + min + ", " + cap + ", " + price + ", 0);";
        try {
            Log.d("PENIS", s);
            return db.insert(s);
        }catch(Exception e){
            Log.d("PENIS", e.getLocalizedMessage());
            return false;
        }
    }
}
