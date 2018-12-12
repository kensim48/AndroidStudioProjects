package com.example.kensi.a2017_quiz2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class FolksongsDbHelper extends SQLiteOpenHelper {

    Context context;
    JSONArray jsonArray;
    public static final int DATABASE_VERSION = 16;
    private static FolksongsDbHelper folksongsDbHelper;
    SQLiteDatabase readableDb = null;
    SQLiteDatabase writeDb = null;

    private FolksongsDbHelper(Context context, JSONArray jsonArray) {
        super(context, FolksongsDatabase.FolksongsTable.TABLE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.jsonArray = jsonArray;
    }

    static FolksongsDbHelper createFolksongsDbHelper(Context context, JSONArray jsonArray) {
        if (folksongsDbHelper == null) {
            folksongsDbHelper = new FolksongsDbHelper(context.getApplicationContext(), jsonArray);
        }
        Log.i(TAG, "Test " + jsonArray);
        return folksongsDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(FolksongsDatabase.FolksongsTable.SQL_CREATE_TABLE);
        fillTable(sqLiteDatabase);
    }

    private void fillTable(SQLiteDatabase sqLiteDatabase){
        Log.i(TAG, "fillTable: " + jsonArray);

        //parse the Json file and store data in the ArrayList using the CharaData class
        try{
            for(int i = 0; i <= jsonArray.length(); i++){
                String title = jsonArray.getJSONObject(i).getString("title");
                String country = jsonArray.getJSONObject(i).getString("country");

                ContentValues cv = new ContentValues();
                cv.put(FolksongsDatabase.FolksongsTable.COL_TITLE, title);
                cv.put(FolksongsDatabase.FolksongsTable.COL_COUNTRY, country);
                sqLiteDatabase.insert(FolksongsDatabase.FolksongsTable.TABLE_NAME,null,cv);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    //TODO 7.6 Complete onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(FolksongsDatabase.FolksongsTable.SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public String queryOneRow(String country){
        if( readableDb == null){
            Log.i(TAG, "queryOneRow: aaaa");
            readableDb = getReadableDatabase();
        }

        Log.i(TAG, "queryOneRow: ");
        final String sqlsel = FolksongsDatabase.FolksongsTable.SQL_SELECT + "'" + country +"'";
        Log.i(TAG, "queryOneRow: sqsel "+ sqlsel);
        Cursor cursor = readableDb.rawQuery(sqlsel,
                null);

        return getDataFromCursor(cursor);
    }

    private String getDataFromCursor(Cursor cursor){


        String song =null;

        cursor.moveToPosition(0);
        int nameIndex = cursor.getColumnIndex(FolksongsDatabase.FolksongsTable.COL_TITLE);
        Log.i(TAG, "getDataFromCursor: "+String.valueOf(nameIndex));
        try {
            song = cursor.getString(nameIndex);
            return song;
        } catch (Exception e){
            return "nil";
        }
    }



    //TODO 2.1 - override the necessary methods
    //TODO 2.2 - write the method that fills the table when it is created

}