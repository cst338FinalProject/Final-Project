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
            /**Customer Table Schema**
             *CREATE TABLE customers (
             *name        varchar(36) not null,
             *id          numeric autoincrement,
             *email       varchar(16) not null,
             *password    varchar(16) not null,
             *username    varchar(16) not null,
             *admin       boolean,
             *primary key (id));
             */

            /**Project spec wants default admin generated...
             * INSERT INTO customers (name, email, password, username, admin) admin,
             * admin@admin.admin, !!aDMIN, !!aDMIN, true;
             */
        }
        try {
            //TODO:Check if flights.db exists, if not, fall to catch
            //TODO:In catch, create new file for flights table and insert default flights
            throw new FileNotFoundException();
        } catch (FileNotFoundException f) {
            /**Flight Table Schema**
             * CREATE TABLE flights (
             * id   varchar(6) not null unique,
             * departLoc    varchar(20) not null,
             * destinLoc    varchar(20) not null,
             * departTime   datetime,
             * flightCap    numeric not null,
             * claimedSeats numeric not null,
             * primary key (id));
             */


            /**After table creation, project spec says need specific flights by default...
             * INSERT INTO flights(departLoc, destinLoc, departTime, flightCap) x, y, z, a;
             */
        }
        try {
            //TODO:Check if reservations.db exists, if not, fall to catch.
            //TODO:In the catch, create new file for reservations table.
            throw new FileNotFoundException();
        } catch (FileNotFoundException r) {
            /**Reservation Table Schema
             * CREATE TABLE reservations(
             * id   numeric not null autoincrement,
             * customerID   numeric not null,
             * seatsReq     numeric not null,
             * flightNum    numeric not null,
             * primary key (id));
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
