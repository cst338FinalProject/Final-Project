/*
 *  Title: Database.java

 *  Abstract: Handles all interactions with the SQLiteDatabase

 *  Authors: Jesus A. Bernal Lopez
 *           Mike Menendez

 *  Date: 12-14-2018
 */

package com.jesusandresbernallopez.project2;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public Database(Context context) {
        super(context, "database", null, 1);
    }

    public boolean insert(String s) {

        db = getWritableDatabase();

        try {
            if (s.contains("\"; ")) {
                db.close();
                return false;
            }

            db.execSQL(s);
            db.close();

            return true;

        } catch (Exception e) {

            db.close();
            return false;
        }
    }

    public Cursor logLookUp(String s) {

        db = getReadableDatabase();
        return db.rawQuery(s, null);
    }

    public Cursor lookup(String s) {

        db = getReadableDatabase();

        if (s.contains("\"; ")) {
            db.close();
            return null;
        }
        Cursor c = db.rawQuery(s, null);
        return c;
    }

    public boolean delete(String s) {

        db = getWritableDatabase();

        if (s.contains("\"; ") || s.contains("'; ")) {
            db.close();
            return false;
        }
        try {

            db.execSQL(s);
            db.close();
            return true;

        } catch (Exception e) {
            db.close();
            return false;
        }
    }

    public boolean update(String s) {
        db = getWritableDatabase();
        if (s.contains("\"; ")) {

            db.close();
            return false;
        }
        try {
            db.execSQL(s);
            db.close();

            return true;
        } catch (SQLException e) {

            db.close();

            return false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /**Log Table Schema**/

        String s = "CREATE TABLE log (" +
                "timestamp    datetime not null,\n" +
                "event        varchar(48) not null,\n" +
                "entry_id     integer primary key autoincrement);";

        db.execSQL(s);

        /**Customer Table Schema**/
        s = "CREATE TABLE customers (\n" +
                "id          integer primary key autoincrement,\n" +
                "password    varchar(16) not null,\n" +
                "username    varchar(16) not null unique);";

        db.execSQL(s);

        /**Project spec wants default admin generated...**/
        s = "INSERT INTO customers (password, username) VALUES('!admiM2', '!admiM2');";

        db.execSQL(s);

        /**Project spec wants pre-generated accounts added**/
        s = "INSERT INTO customers (password, username) VALUES('@cSit100', 'A@lice5');";

        db.execSQL(s);

        s = "INSERT INTO customers (password, username) VALUES('123aBc##', '$BriAn7');";

        db.execSQL(s);

        s = "INSERT INTO customers (password, username) VALUES('CHrIS12!!', '!chriS12!');";

        db.execSQL(s);

        /**Flight Table Schema**/
        s = "CREATE TABLE flights (\n" +
                "name         varchar(20) not null unique,\n" +
                "departLoc    varchar(20) not null,\n" +
                "destinLoc    varchar(20) not null,\n" +
                "departHour   integer check (departHour<24) check(departHour>=0),\n" +
                "departMin    integer check (departMin <60) check(departMin >=0),\n" +
                "flightCap    integer not null,\n" +
                "price        decimal not null,\n" +
                "claimedSeats integer not null,\n" +
                "primary key (name));";

        db.execSQL(s); // crashed with no flights prior to adding this

        /**Project spec wants default generated flights**/
        s = "INSERT INTO flights (name, departLoc, destinLoc, departHour, departMin, flightCap, price, claimedSeats)" +
                " VALUES ('Otter101', 'Monterey', 'Los Angeles', 10, 30, 10, 150.00, 0);";

        db.execSQL(s);

        s = "INSERT INTO flights (name, departLoc, destinLoc, departHour, departMin, flightCap, price, claimedSeats)" +
                " VALUES ('Otter102', 'Los Angeles', 'Monterey', 13, 00, 10, 150.00, 0);";

        db.execSQL(s);

        s = "INSERT INTO flights (name, departLoc, destinLoc, departHour, departMin, flightCap, price, claimedSeats)" +
                " VALUES ('Otter201', 'Monterey', 'Seattle', 11, 00, 5, 200.50, 0);";

        db.execSQL(s);

        s = "INSERT INTO flights (name, departLoc, destinLoc, departHour, departMin, flightCap, price, claimedSeats)" +
                " VALUES ('Otter205', 'Monterey', 'Seattle', 15, 45, 15, 150.00, 0);";

        db.execSQL(s);

        s = "INSERT INTO flights (name, departLoc, destinLoc, departHour, departMin, flightCap, price, claimedSeats)" +
                " VALUES ('Otter202', 'Seattle', 'Monterey', 14, 10, 5, 200.00, 0);";

        db.execSQL(s);

        /**Reservation Table Schema**/
        s = "CREATE TABLE reservations(\n" +
                "id          integer primary key autoincrement,\n" +
                "seatsReq    integer not null," +
                "flight_name varchar(20),\n" +
                "customer_id integer);"; //+
//                "foreign key (flight_name) references flights (name) on delete cascade,\n" +
//                "foreign key (customer_id) references customers (id) on delete cascade);";

        db.execSQL(s);

        /** Getting T R I G G E R E D **/

        s = "CREATE TRIGGER cust_mod AFTER UPDATE ON customers BEGIN INSERT INTO log(event, timestamp) VALUES('update customers', datetime('NOW')); END;";

        db.execSQL(s);

        s = "CREATE TRIGGER flight_mod AFTER UPDATE ON flights BEGIN INSERT INTO log(event, timestamp) VALUES('udpate flight', datetime('NOW')); END;";

        db.execSQL(s);

        s = "CREATE TRIGGER res_mod AFTER UPDATE ON reservations BEGIN INSERT INTO log(event, timestamp) VALUES('update reservation', datetime('NOW')); END;";

        db.execSQL(s);

        s = "CREATE TRIGGER new_cust AFTER INSERT ON customers BEGIN INSERT INTO log(event, timestamp) VALUES ('new customer', datetime('NOW')); END;";

        db.execSQL(s);

        s = "CREATE TRIGGER new_res AFTER INSERT ON reservations BEGIN INSERT INTO log(event, timestamp) VALUES ('new reservation', datetime('NOW')); END;";

        db.execSQL(s);

        s = "CREATE TRIGGER new_flight AFTER INSERT ON flights BEGIN INSERT INTO log(event, timestamp) VALUES ('new flight', datetime('NOW')); END;";

        db.execSQL(s);

        s = "CREATE TRIGGER del_cust AFTER DELETE ON customers BEGIN INSERT INTO log(event, timestamp) VALUES ('customer deleted', datetime('NOW')); END;";

        db.execSQL(s);

        s = "CREATE TRIGGER del_flight AFTER DELETE ON flights BEGIN INSERT INTO log(event, timestamp) VALUES ('flight deleted', datetime('NOW')); END;";

        db.execSQL(s);

        s = "CREATE TRIGGER del_res AFTER DELETE ON reservations BEGIN INSERT INTO log(event, timestamp) VALUES ('reservation deleted', datetime('NOW')); END;";

        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
