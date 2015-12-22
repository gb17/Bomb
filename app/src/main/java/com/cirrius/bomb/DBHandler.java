package com.cirrius.bomb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by admin on 12/22/15.
 */
public class DBHandler extends SQLiteOpenHelper {

    public static String DB_NAME = "BOMB";
    public static String TABLE_NAME = "TBGRID";
    public static int DB_VERSION = 1;
    Context context;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        Toast.makeText(context, "M here", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (COL0 int auto increment, COL1 text, COL2 text, COL3 text, COL4 text, COL5 text, COL6 text, COL7 text, COL8 text, COL9 text, COL10 text, COL11 text, COL12 text, COL13 text, COL14 text, COL15 text  )";
        Toast.makeText(context, "M here2", Toast.LENGTH_LONG).show();
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
