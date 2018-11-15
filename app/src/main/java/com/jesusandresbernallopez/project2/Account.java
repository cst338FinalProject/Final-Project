/**
 * IMPORTANT!!! THESE ARE JUST TEMP FUNCTION IMPLEMENTATIONS, WILL BE IMPLEMENTED WITH
 * SQL QUERIES!!!
 * The constructor will be called post database query so that we can dynamically
 * create a customer and compare with a temp instance for authentication.
 * This will allow us to not keep all customers in memory.
 **/

package com.jesusandresbernallopez.project2;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Account {

    private String customerName;
    private int id;
    private ArrayList<Reservation> reservations;
    private String username;
    private String password;
    private boolean update;

    public Account(String s) {
        reservations = new ArrayList<>();
        update = false;
        StringTokenizer st = new StringTokenizer(s);
        customerName = st.nextToken();
        id = Integer.parseInt(st.nextToken());
        username = st.nextToken();
        password = st.nextToken();
        while (st.hasMoreTokens()) {
            int r = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            String f = st.nextToken();
            newReservation(n, f, r);
        }
    }


    public String getCustomerName() {
        return customerName;
    }

    public int getId() {
        return id;
    }

    public void newReservation(int n, String f, int r) {
        reservations.add(new Reservation(n, f, r));
    }

    public boolean checkUsername(String s) {
        return s.equals(this.customerName);
    }

    public boolean checkPassword(String s) {
        return s.equals(this.password);
    }

    public boolean updateUser(String s) {
        if (Database.userlookup()) {
            return false;
        }
        username = s;
        update = true;
        return true;
    }

    public void updatePass(String s) {
        password = s;
        update = true;
    }

    public boolean writeToDB() {
        //If the customer was updated, the flag will require us to update the customer in the table
        //If customer wasn't updated, we can return true because no need to update, false is only
        //If something went wrong and will be loggeed in the Database class
        if (update) {
            String q;
            q = "update customers " + customerName + ", " + username + ", " + ", " + " where id = " + id;
            return Database.insert(q);
        }
        return true;
    }
}
