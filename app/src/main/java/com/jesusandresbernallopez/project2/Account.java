/**
 * IMPORTANT!!! THESE ARE JUST TEMP FUNCTION IMPLEMENTATIONS, WILL BE IMPLEMENTED WITH
 * SQL QUERIES!!!
 * The constructor will be called post database query so that we can dynamically
 * create a customer and compare with a temp instance for authentication.
 * This will allow us to not keep all customers in memory.
 **/

package com.jesusandresbernallopez.project2;

public class Account {

    private String username;
    private String password;
    private Database db;

    public Account(Database d) {
        db = d;
    }

    public void createAccount(String uname, String pass;) {
        this.username = uname;
        this.password = pass;
    }

    public void custLookup(int id) {

    }

    public void verifyCust(String uname, String pass) {

        String s = "SELECT * FROM customer WHERE uname = " + uname + ";";

        db.lookup(s);

    }


/**     //TODO:Properly parse through s for ID, then query to see if they exist
        //TODo:If false, create new customer

        String q = "SELECT * FROM customers WHERE " + s;
        String l = Database.lookup(q);
        if (l == "false") {
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
    }


    public String getCustomerName() {
        return customerName;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Reservation> getAllReservations() {
        return reservations;
    }

    public Reservation getReservation(int rID) {
        if (this.reservations.contains(rID)) {
            return reservations.get(rID);
        } else {
            return null;
        }
    }

    public boolean deleteAccount() {
        String s = "DELETE FROM customers WHERE ID=" + this.id;
        return Database.delete(s);
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
        //if (Database.userlookup() != "") {
        //    return false;
        //  }
        username = s;
        update = true;
        return true;
    }

    //sets this instance's password to new pass and updates flag to force instance update in DB.
    public boolean updatePass(String s) {
        update = true;
        //TODO: Fix this query to be properly parsed
        String q = "UPDATE customers WHERE ID=" + this.id;
        return Database.update(q);
    }

    public boolean writeToDB() {
        //If the customer was updated, the flag will require us to update the customer in the table
        //If customer wasn't updated, we can return true because no need to update, false is only
        //If something went wrong and will be loggeed in the Database class
        if (update) {
            String q;
            q = "update customers " + customerName + ", " + username + ", " + ", " + " where id = " + id;
            return Database.update(q);
        }
        return true;
    }
 **/


}
