package com.jesusandresbernallopez.project2;

import android.database.Cursor;

public class Account {

    public Account(Database d) {
    }

    public void createAccount(String uname, String pass, Database db) {

        String s = "INSERT INTO customers (username, password) VALUES (" + uname + ", " + pass + ");";
        db.insert(s);

    }

    public boolean verifyCust(String uname, String pass, Database db) {

        String s = "SELECT * FROM customer WHERE username = " + uname + ";";

        String r = db.lookup(s);

        return r.contains(pass);

    }

    public boolean adminVerify(String uname, String pass) {
        return uname == "!admiM2" && pass == "!admiM2";
    }

    public Cursor dispLog(Database db) {
        String s = "SELECT * FROM log order by timestamp desc;";

        return db.logLookUp(s);
    }
}
