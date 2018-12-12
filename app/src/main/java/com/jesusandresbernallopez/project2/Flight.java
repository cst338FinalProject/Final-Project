package com.jesusandresbernallopez.project2;

import android.util.Log;

import java.util.ArrayList;

public class Flight {

    public Flight() {
    }

    public ArrayList<String> flightSearch(int tickets, String dLoc, String aLoc, Database db) {

//        "name         varchar(20) not null unique,\n" +
//                "departLoc    varchar(20) not null,\n" +
//                "destinLoc    varchar(20) not null,\n" +
//                "departTime   integer,\n" +
//                "flightCap    integer not null,\n" +
//                "claimedSeats integer not null,\n" +
//                "price        decimal not null,\n" +
//                "primary key (name));";

        String s = "SELECT * FROM flights WHERE flightCap > " + tickets + " AND departLoc = '" + dLoc + "' AND destinLoc = '" + aLoc + "';";

       try {
           if (db.lookup(s) == null) {
               throw new Exception("fuck this");
           }

//           Log.d("sdkfjgnakfnj\n\n\n\n\n\n\n\n\n\n\n", db.logLookUp(s).getColumnName(0));
           return db.lookup(s);
       }catch( Exception e){
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
}
