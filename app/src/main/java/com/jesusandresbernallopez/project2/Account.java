package com.jesusandresbernallopez.project2;

import android.database.Cursor;

/**
 * "CREATE TABLE customers (\n" +
 * "id          integer primary key autoincrement,\n" +
 * "password    varchar(16) not null,\n" +
 * "username    varchar(16) not null unique);";
 */

public class Account {

    public Account() {
    }

    public boolean createAccount(String uname, String pass, Database db) {

        String s = "INSERT INTO customers (password, username) VALUES('" + pass + "', '" + uname +"');";

        return db.insert(s);
    }

    public boolean verifyCust(String uname, String pass, Database db) {

        String s = "SELECT password FROM customers WHERE username = '" + uname + "';";
        Cursor c = db.lookup(s);

        c.moveToFirst();

        if (c.getCount() == 0) {
            return false;
        }

        String r = c.getString(1);
        c.close();

        return r.equals(pass);
    }

    public int getCustomerID(String uname, Database db) {
        String s = "SELECT c_id FROM customers WHERE username = '" + uname + "';";
        Cursor c = db.lookup(s);

        if (c.getCount() == 0) {
            return -1;
        } else {
            c.moveToFirst();
            int temp = c.getInt(0);
            c.close();
            return temp;
        }
    }

    public boolean adminVerify(String uname, String pass) {
        return uname.equals("!admiM2") && pass.equals("!admiM2");
    }

    public Cursor dispLog(Database db) {

        String s = "SELECT * FROM log order by timestamp desc;";

        return db.logLookUp(s);
    }
}
