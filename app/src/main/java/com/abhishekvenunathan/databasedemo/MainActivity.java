package com.abhishekvenunathan.databasedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            SQLiteDatabase eventDatabase = this.openOrCreateDatabase("Events",Context.MODE_PRIVATE,null);
            eventDatabase.execSQL("CREATE TABLE IF NOT EXISTS events(name VARCHAR,year int(4))");
            eventDatabase.execSQL("INSERT INTO events (name,year) VALUES ('millenium',2000)");
            eventDatabase.execSQL("INSERT INTO events (name,year) VALUES ('my birth',1995)");

            Cursor c = eventDatabase.rawQuery("SELECT * from events",null);

            int nameIndex = c.getColumnIndex("name");
            int yearIndex = c.getColumnIndex("year");

            c.moveToFirst();

            while(c!=null){
                Log.i("results name:",c.getString(nameIndex));
                Log.i("results year:",Integer.toString(c.getInt(yearIndex)));
                c.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
