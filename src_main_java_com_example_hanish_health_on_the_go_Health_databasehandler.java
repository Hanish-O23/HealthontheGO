package com.example.hanish.health_on_the_go;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class Health_databasehandler extends SQLiteAssetHelper {

    //The Android's default system path of your application database.
    //replace com.binarybricks.shippingwithsqllite with you Application package nae
    //This should be same as which you used package section in your manifest


    //replace this with name of your db file which you copied into asset folder
    private static String DB_NAME = "DISEASES.db";
    private static int Database_version =1;

    public Health_databasehandler(Context context) {
        super(context,DB_NAME,null,Database_version);
    }
}



