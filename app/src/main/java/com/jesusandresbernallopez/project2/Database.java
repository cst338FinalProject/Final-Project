package com.jesusandresbernallopez.project2;

import java.io.File;
import java.io.FileNotFoundException;

public class Database {


    public Database() {

        try {
            //TODO:Check if customer.db exists, if not, fall to catch.
            //TODO:In the catch, create new file for customer table and insert admin default account

            throw new FileNotFoundException();
        } catch (FileNotFoundException c) {

            File customerdb = new File("customerTable.db");
            String s;
            /**Customer Table Schema**
             *s = "CREATE TABLE customers (
             *name        varchar(36) not null,
             *id          numeric autoincrement,
             *email       varchar(16) not null,
             *password    varchar(16) not null,
             *username    varchar(16) not null,
             *admin       boolean,
             *primary key (id));";
             */

            /**Project spec wants default admin generated...
             * s = "INSERT INTO customers (name, email, password, username, admin) admin,
             * admin@admin.admin, !admiM2, !admiM2, true;";
             */

            /**Project spec wants pre-generated accounts added
             * s = "INSERT INTO customers (name, email, password, username, admin) \"Alice\", \"alice@csumb.edu\",
             * \"@cSit100\", \"A@lice5\", false;";
             *
             * s = "INSERT INTO customers (name, email, password, username, admin) \"Brian\", \"brian@csumb.edu\",
             * \"123aBc##\", \"$BriAn7\", false;";
             *
             * s = "INSERT INTO customers (name, email, password, username, admin) \"Chris\", \"chris@csumb.edu\",
             * \"CHrIS12!!\", \"!chriS12!\", false;";
             */
        }
        try {
            //TODO:Check if flights.db exists, if not, fall to catch
            //TODO:In catch, create new file for flights table and insert default flights
            throw new FileNotFoundException();
        } catch (FileNotFoundException f) {
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
             * primary key (id));";
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
            String s;
            /**Reservation Table Schema
             * s = "CREATE TABLE reservations(
             * id   numeric not null autoincrement,
             * customerID   numeric not null,
             * seatsReq     numeric not null,
             * flightNum    numeric not null,
             * primary key (id));";
             */

        }

    }

    public static boolean insert(String q) {
        return true;
    }

    public static boolean userlookup() {
        return true;
    }
}
