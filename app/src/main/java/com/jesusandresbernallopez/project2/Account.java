package com.jesusandresbernallopez.project2;

import android.database.Cursor;
import android.util.Log;

/**
 * "CREATE TABLE customers (\n" +
 * "id          integer primary key autoincrement,\n" +
 * "password    varchar(16) not null,\n" +
 * "username    varchar(16) not null unique);";
 */

public class Account {

    public Account(){

    }

    public Account(Database d) {
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
            //returns false since count of 0 means no entries with that customer username
            return false;
        }
        String r = c.getString(1);
        Log.d("Result", r);

        return r.equals(pass);

    }

    public int getCustomerID(String uname, Database db) {
        String s = "SELECT c_id FROM customers WHERE username = '" + uname + "';";
        Cursor c = db.lookup(s);
        if (c.getCount() == 0) {
            return -1;
        } else {
            c.moveToFirst();
            return c.getInt(0);
        }
    }

    public boolean adminVerify(String uname, String pass) {
        return uname.equals("!admiN2") && pass.equals("!admiN2");
    }

    public Cursor dispLog(Database db) {
        String s = "SELECT * FROM log order by timestamp desc;";

        return db.logLookUp(s);
    }
}
