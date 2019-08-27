package com.example.hanish.health_on_the_go;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Hanish on 09-03-2019.
 */

public class Database_acess {
    private SQLiteDatabase db;
    private Health_databasehandler hdb;
    private static Database_acess instance;
    Cursor c = null;

    public Database_acess(Context context) {

        this.hdb = new Health_databasehandler(context);
    }

    public static Database_acess getInstance(Context context) {
        if(instance==null)
        {
            instance = new Database_acess(context);
        }
        return instance;
    }
    public void open()
    {
        this.db=hdb.getWritableDatabase();

    }
    public void close()
    {
        if (db!=null)
        {
            this.db.close();
        }
    }
    public String getQuestion(String No)
    {
        StringBuffer buffer = new StringBuffer();
        try {
            c = db.rawQuery("select Title from diseases where SR_NO='"+No+"'",new String[]{});

            while (c.moveToNext())
            {
                String question = c.getString(0);
                buffer.append(""+question);

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();

            buffer.append(e.toString());

        }

        return buffer.toString();


    }
    public String getDisease(String No)
    {
        StringBuffer buffer = new StringBuffer();
        try {
            c = db.rawQuery("select Disease from diseases where SR_NO='"+No+"'",new String[]{});

            while (c.moveToNext())
            {
                String question = c.getString(0);
                buffer.append(""+question);

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();

            buffer.append(e.toString());

        }

        return buffer.toString();


    }
}
