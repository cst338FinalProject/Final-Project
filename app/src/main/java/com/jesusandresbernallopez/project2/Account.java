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

        String st = "SELECT * FROM customers;";//"SELECT username, password FROM customers WHERE username = '" + uname + "';";
        Log.d("test", Integer.toString(db.lookup(st).size()));
        if(db.lookup(st).size() != 0){
            //checks to make sure user doesn't exist already.
            Log.d("Error creating account", "Account already exist");
            return false;
        }

        String s = "INSERT INTO customers (password, username) VALUES('" + pass + "', '" + uname +"');";

        return db.insert(s);

    }

    public boolean verifyCust(String uname, String pass, Database db) {

        String s = "SELECT * FROM customer WHERE username = '" + uname + "';";

        ArrayList<String> r = db.lookup(s);

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
