/*
 *  Title: Flight.java

 *  Abstract: Flight.java is responsible for searching flights, reserving a seat, and adding a flight.

 *  Authors: Jesus A. Bernal Lopez
 *           Mike Menendez

 *  Date: 12-14-2018
 */

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
        try {
            if (db.lookup(s) == null) {
                throw new Exception("Found no flights");
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
            System.exit(2);
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
            return db.insert(s);
        }catch(Exception e){
            return false;
        }
    }
}
