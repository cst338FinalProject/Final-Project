package com.jesusandresbernallopez.project2;

import android.util.Log;

import java.util.ArrayList;

public class Flight {

    public Flight() {
    }

    public ArrayList<String> flightSearch(int tickets, String dLoc, String aLoc, Database db) {

        String s = "SELECT * FROM flights WHERE flightCap > " + tickets + " AND departLoc = '" + dLoc + "' AND destinLoc = '" + aLoc + "';";

//       try {
//           if (db.lookup(s) == null) {
//               throw new Exception("fuck this");
//           }
           return db.lookup(s);
//       }catch( Exception e){
//           System.exit(69);
//           return null;
//        }
    }

    public boolean reserveSeat(int i) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean addFlight(String name, String dep, String arriv, int time, int cap, int price, Database db){

        String s = "INSERT INTO flights (name, departLoc, destinLoc, departTime, flightCap, price, claimedSeats)" +
                " VALUES ('"+ name + "', '" + dep +"', '"+ arriv +"', " + time + ", " + cap + ", " + price + ", 0);";

        return db.insert(s);
    }
}
