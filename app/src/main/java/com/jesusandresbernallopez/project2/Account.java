package com.jesusandresbernallopez.project2;

import android.database.Cursor;
import android.util.Log;

import java.util.regex.Pattern;

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
        String specialChar = "~`!@#$%^&*()-_=+\\\\|[{]};:'\\\",<.>/?";
        String nums = "0123456789";
        int count = 0, counter = 0;
        boolean num = false, numTwo = false;

        for(int i = 0; i < nums.length(); i++){
            if(uname.contains(Character.toString(nums.charAt(i)))){
                num = true;
                break;
            }
        }

        for(int i = 0; i < nums.length(); i++){
            if(pass.contains(Character.toString(nums.charAt(i)))){
                numTwo = true;
                break;
            }
        }

        for(int i = 0; i < specialChar.length(); i++){
            if(uname.contains(Character.toString(specialChar.charAt(i)))){
                count += 1;
                break;
            }
        }

        for(int i = 0; i < specialChar.length(); i++){
            if(pass.contains(Character.toString(specialChar.charAt(i)))){
                counter += 1;
                break;
            }
        }

        if (count == 0 || counter == 0 || !num || !numTwo){
            return false;
        }
        String s = "INSERT INTO customers (password, username) VALUES('" + pass + "', '" + uname +"');";
        Log.d("SomethingThatIsObvious", "Account");

        return db.insert(s);
    }

    public boolean verifyCust(String uname, String pass, Database db) {

        String s = "SELECT password FROM customers WHERE username = '" + uname + "';";
        Cursor c = db.lookup(s);

        c.moveToFirst();

        if (c.getCount() == 0) {
            return false;
        }

        String r = c.getString(0);
        c.close();

        return r.equals(pass);
    }

    public int getCustomerID(String uname, Database db) {
        String s = "SELECT id FROM customers WHERE username = '" + uname + "';";
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