package com.jesusandresbernallopez.project2;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

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

        String s = "SELECT * FROM customers WHERE username = '" + uname + "';";

        ArrayList<String> r = db.lookup(s);
        Log.d("Result", r.get(0));

        return r.contains(pass);

    }

    public boolean adminVerify(String uname, String pass) {
        return uname.equals("!admiN2") && pass.equals("!admiN2");
    }

    public Cursor dispLog(Database db) {
        String s = "SELECT * FROM log order by timestamp desc;";

        return db.logLookUp(s);
    }
}
