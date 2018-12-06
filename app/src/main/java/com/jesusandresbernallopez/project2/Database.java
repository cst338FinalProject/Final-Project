package com.jesusandresbernallopez.project2;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Database {

    SQLiteDatabase db;

    public Database() {

    }

    //Takes a string with precrafted sql insert statement. This function is simply handling whether insert pass/fail
    public static boolean insert(String s) {
        try {
            if (s.contains("\"; ")) {
                Log.d("SQL Injection", "User attempted custom SQL");
                return false;
            }
            //database.exec(q);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Takes a string with precrafted sql query. This function returns the resultant.
    public static String lookup(String s) {

        if (s.contains("\"; ")) {
            Log.d("SQL Injection", "User attempted custom SQL");
            return null;
        }
        //return database.exec(q);
        return null;
    }

    public static boolean delete(String s) {
        if (s.contains("\"; ")) {
            Log.d("SQL Injection", "User attempted custom SQL");
            return false;
        }
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean update(String s) {
        if (s.contains("\"; ")) {
            Log.d("SQL Injection", "User attempted custom SQL");
            return false;
        }
        try {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
