package com.jesusandresbernallopez.project2;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public Database(Context context) {
        super(context, "database", null, 0);
    }


    //Takes a string with precrafted sql insert statement. This function is simply handling whether insert pass/fail
    public boolean insert(String s) {

        db = getWritableDatabase();

        try {
            if (s.contains("\"; ")) {
                Log.d("SQL Injection", "User attempted custom SQL");
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

    //Takes a string with precrafted sql query. This function returns the resultant.
    public String lookup(String s) {

        db = getReadableDatabase();

        if (s.contains("\"; ")) {
            Log.d("SQL Injection", "User attempted custom SQL");
            db.close();
            return null;
        }

        //TODO: using a cursor obj, iterate through and parse resultant string r
        //TODO: Implementation is def broken so fix this. Only the logic is in place

        ArrayList<String> list = new ArrayList<>();
        String r = new String();
        Cursor c = db.rawQuery(s, null);

        for (int i = 0; ; i++) {

            c.moveToNext();

            if (c.isLast()) {
                break;
            }
            list.add(c.getString(i));
        }

        c.close();

        for (int i = 0; i < list.size(); i++) {
            r = r + list.get(i) + "\n";
        }


        db.close();
        return r;
    }

    public boolean delete(String s) {

        db = getWritableDatabase();

        if (s.contains("\"; ") || s.contains("'; ")) {
            Log.d("SQL Injection", "User attempted custom SQL");
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
        if (s.contains("\"; ")) {

            Log.d("SQL Injection", "User attempted custom SQL");
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

        //TODO:Add triggers on each of the other tables to auto-populate log table

        /**Log Table Schema**/

        String s = "CREATE TABLE log (" +
                "timestamp    datetime not null,\n" +
                "user         varchar(36) not null,\n" +
                "entry_id     integer primary key autoincrement,\n" +
                "description  varchar(40));";

        db.execSQL(s);

        /**Customer Table Schema**/
        s = "CREATE TABLE customers (\n" +
                "id          integer primary key autoincrement,\n" +
                "password    varchar(16) not null,\n" +
                "username    varchar(16) not null unique,\n" +
                "admin       boolean);";

        db.execSQL(s);

        /**Project spec wants default admin generated...**/
        s = "INSERT INTO customers (name, email, password, username, admin)" +
                " VALUES(\"admin\", \"admin@admin.admin\", \"!admiM2\", \"!admiM2\", true);";

        db.execSQL(s);

        /**Project spec wants pre-generated accounts added**/
        s = "INSERT INTO customers (name, email, password, username, admin) VALUES(\"Alice\", \"alice@csumb.edu\", " +
                "\"@cSit100\", \"A@lice5\", false);";

        db.execSQL(s);

        s = "INSERT INTO customers (name, email, password, username, admin) VALUES(\"Brian\", \"brian@csumb.edu\"," +
                "\"123aBc##\", \"$BriAn7\", false);";

        db.execSQL(s);

        s = "INSERT INTO customers (name, email, password, username, admin) VALUES(\"Chris\", \"chris@csumb.edu\"," +
                "\"CHrIS12!!\", \"!chriS12!\", false);";

        db.execSQL(s);

        /**Flight Table Schema**/
        s = "CREATE TABLE flights (\n" +
                "name         varchar(20) not null unique,\n" +
                "departLoc    varchar(20) not null,\n" +
                "destinLoc    varchar(20) not null,\n" +
                "departTime   integer,\n" +
                "flightCap    integer not null,\n" +
                "claimedSeats integer not null,\n" +
                "price        decimal not null,\n" +
                "primary key (name));";

        /**Project spec wants default generated flights**/
        s = "INSERT INTO flights (name, departLoc, destinLoc, departTime, flightCap, price, claimedSeats)" +
                " VALUES (\"Otter101\", \"Monterey\", \"Los Angeles\", 1030, 10, 150.00, 0);";

        db.execSQL(s);

        s = "INSERT INTO flights (name, departLoc, destinLoc, departTime, flightCap, price, claimedSeats)" +
                " VALUES (\"Otter102\", \"Los Angeles\", \"Monterey\", 1300, 10, 150.00, 0);";

        db.execSQL(s);

        s = "INSERT INTO flights (name, departLoc, destinLoc, departTime, flightCap, price, claimedSeats)" +
                " VALUES (\"Otter201\", \"Monterey\", \"Seattle\", 1100, 5, 200.50, 0);";

        db.execSQL(s);

        s = "INSERT INTO flights (name, departLoc, destinLoc, departTime, flightCap, price, claimedSeats)" +
                " VALUES (\"Otter205\", \"Monterey\", \"Seattle\", 1545, 15, 150.00, 0);";

        db.execSQL(s);

        s = "INSERT INTO flights (name, departLoc, destinLoc, departTime, flightCap, price, claimedSeats)" +
                " VALUES (\"Otter202\", \"Seattle\", \"Monterey\", 1410, 5, 200.00, 0);";

        db.execSQL(s);

        /**Reservation Table Schema**/
        s = "CREATE TABLE reservations(\n" +
                "id          integer primary key autoincrement,\n" +
                "seatsReq    integer not null,\n" +
                "foreign key (flight_name) references flights (name) on delete cascade,\n" +
                "foreign key (customer_id) references customers (id) on delete cascade);";

        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        /**
         * Since we are not implementing upgrades of this database due to being a class assignment
         * This method will be empty. If this is to be expanded on (it wont)
         * pass in the database, the version numerial and the upgraded version numerial.
         * **/

    }
}
