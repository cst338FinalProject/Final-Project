package com.jesusandresbernallopez.project2;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Database {

    SQLiteDatabase db;

    public Database() {

        //TODO: Create the initialize the database as empty.
        //db = SQLiteDatabase.create();
        File log = new File("log.db");

        try {
            //TODO:Check if the log.db exists, if not, fall to catch.
            //TODO:In the catch, create new file for log table.
            //TODO:Add triggers on each of the other tables.

            if (!log.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException l) {

            try {
                log.createNewFile();
            } catch (IOException f) {
                System.out.println("Error creating log.db file");
            }

            Log.d("Creating new file", "Creating log.db.");

            /**Log Table Schema**/

            String s = new String("CREATE TABLE log (" +
                    "timestamp    datetime not null,\n" +
                    "user         varchar(36) not null,\n" +
                    "entry_id     numeric autoincrement,\n" +
                    "description  varchar(40),\n" +
                    "primary key (entry_id));");

            db.execSQL(s);

        }

        try {
            //TODO:Check if customer.db exists, if not, fall to catch.
            //TODO:In the catch, create new file for customer table and insert admin default account

            throw new FileNotFoundException();
        } catch (FileNotFoundException c) {

            File customerdb = new File("customerTable.db");
            Log.d("Creating new file", "Creating customers.db and populating");
            String s;
            /**Customer Table Schema**/
            s = new String("CREATE TABLE customers (\n" +
                    "name        varchar(36) not null,\n" +
                    "id          numeric autoincrement,\n" +
                    "email       varchar(16) not null,\n" +
                    "password    varchar(16) not null,\n" +
                    "username    varchar(16) not null,\n" +
                    "admin       boolean,\n" +
                    "primary key (id));");

            db.execSQL(s);

            /**Project spec wants default admin generated...**/
            s = new String("INSERT INTO customers (name, email, password, username, admin)" +
                    "admin, admin@admin.admin, !admiM2, !admiM2, true;");

            db.execSQL(s);

            /**Project spec wants pre-generated accounts added**/
            s = new String("INSERT INTO customers (name, email, password, username, admin) \"Alice\", \"alice@csumb.edu\",\n" +
                    "\"@cSit100\", \"A@lice5\", false;");

            db.execSQL(s);

            s = new String("INSERT INTO customers (name, email, password, username, admin) \"Brian\", \"brian@csumb.edu\"," +
                    "\"123aBc##\", \"$BriAn7\", false;");

            db.execSQL(s);

            s = new String("INSERT INTO customers (name, email, password, username, admin) \"Chris\", \"chris@csumb.edu\"," +
                    "\"CHrIS12!!\", \"!chriS12!\", false;");

            db.execSQL(s);
        }
        try {
            //TODO:Check if flights.db exists, if not, fall to catch
            //TODO:In catch, create new file for flights table and insert default flights
            throw new FileNotFoundException();
        } catch (FileNotFoundException f) {
            File flightsdb = new File("flights.db");
            String s;
            /**Flight Table Schema**
             * s = "CREATE TABLE flights (
             * name         varchar(20) not null unique,
             * departLoc    varchar(20) not null,
             * destinLoc    varchar(20) not null,
             * departTime   numeric integer,
             * flightCap    numeric not null,
             * claimedSeats numeric not null,
             * price        decimal not null,
             * primary key (name));";
             */

            /**Project spec wants default generated flights 
             * s = "INSERT INTO flights (name, departLoc, destinLoc, departTime, flightCap, price, claimedSeats)
             * \"Otter101\", \"Monterey\", \"Los Angeles\", 1030, 10, 150.00, 0;";
             *
             * s = "INSERT INTO flights (name, departLoc, destinLoc, departTime, flightCap, price, claimedSeats)
             * \"Otter102\", \"Los Angeles\", \"Monterey\", 1300, 10, 150.00, 0;";
             *
             * s = "INSERT INTO flights (name, departLoc, destinLoc, departTime, flightCap, price, claimedSeats)
             * \"Otter201\", \"Monterey\", \"Seattle\", 1100, 5, 200.50, 0;";
             *
             * s = "INSERT INTO flights (name, departLoc, destinLoc, departTime, flightCap, price, claimedSeats)
             * \"Otter205\", \"Monterey\", \"Seattle\", 1545, 15, 150.00, 0;";
             *
             * s = "INSERT INTO flights (name, departLoc, destinLoc, departTime, flightCap, price, claimedSeats)
             * \"Otter202\", \"Seattle\", \"Monterey\", 1410, 5, 200.00, 0;";
             */
        }
        try {
            //TODO:Check if reservations.db exists, if not, fall to catch.
            //TODO:In the catch, create new file for reservations table.
            throw new FileNotFoundException();
        } catch (FileNotFoundException r) {

            File reservationdb = new File("reservations.db");
            String s;
            /**Reservation Table Schema
             * s = "CREATE TABLE reservations(
             * id   numeric not null autoincrement,
             * seatsReq     numeric not null,
             * flightNum    numeric not null,
             * foreign key (customer_id) references customers (id) on delete cascade,
             * primary key (id));";
             */

        }

    }

    //Takes a string with precrafted sql insert statement. This function is simply handling whether insert pass/fail
    public static boolean insert(String s) {
        try {
            if (s.contains("\" ") || s.contains("\" ")) {
                Log.d("SQL Injection", "User attempted custom SQL");
                return false;
            }
            //database.exec(q);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Takes a string with precrafted sql query. This function returns the resultant.
    public static String lookup(String s) {

        if (s.contains("\" d") || s.contains("\" D")) {
            Log.d("SQL Injection", "User attempted custom SQL");
            return null;
        }
        //return database.exec(q);
        return null;
    }

    public static boolean delete(String s) {
        if (s.contains("\" d") || s.contains("\" D")) {
            Log.d("SQL Injection", "User attempted custom SQL");
            return false;
        }
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean update(String s) {
        if (s.contains("\" d") || s.contains("\" D")) {
            Log.d("SQL Injection", "User attempted custom SQL");
            return false;
        }
        try {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
