package com.example.kensi.a2017_quiz2;

//— -copy and paste from this line onwards — -

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;


public class FolksongsDbHelper extends SQLiteOpenHelper {

    Context context;
    JSONArray jsonArray;
    public static final int DATABASE_VERSION = 1;

    FolksongsDbHelper(Context context, JSONArray jsonArray){
        super(context, FolksongsDatabase.FolksongsTable.TABLE_NAME, null,DATABASE_VERSION);
        this.context = context;
        this.jsonArray = jsonArray;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FolksongsDatabase.FolksongsTable.SQL_CREATE_TABLE);
        fillTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //TODO 2.1 - override the necessary methods
    //TODO 2.2 - write the method that fills the table when it is created

    private void fillTable(SQLiteDatabase sqLiteDatabase){

        ArrayList<CharaData> arrayList = new ArrayList<>();
        PACKAGE_NAME = context.getPackageName();

        //open the Json file pictures stored in the res/raw folder
        InputStream inputStream = context.getResources().openRawResource(R.raw.pictures);
        String string = MainActivity.convertJsonToString(R.raw.folksongs);

        //parse the Json file and store data in the ArrayList using the CharaData class
        try{
            JSONArray jsonArray = new JSONArray(string);
            for(int i = 0; i <= jsonArray.length(); i++){
                String name = jsonArray.getJSONObject(i).getString("title");
                String description = jsonArray.getJSONObject(i).getString("country");

                arrayList.add(new CharaData(name, description));
            }
        }catch(JSONException e){
            e.printStackTrace();
        }

        //Each entry in the arrayList is stored as a ContentValues object
        //Then this ContentValues object is inserted to the sqLiteDatabase to create a new row
        for(int i = 0; i< arrayList.size(); i++){
            Log.i("Norman","" + arrayList.get(i).getCountry());
            ContentValues cv = new ContentValues();

            cv.put(CharaContract.CharaEntry.COL_NAME, arrayList.get(i).getTitle());
            cv.put(CharaContract.CharaEntry.COL_DESCRIPTION, arrayList.get(i).getCountry());

            String fname = arrayList.get(i).getFile();
            int resId = context.getResources().getIdentifier(fname, "drawable", PACKAGE_NAME);
            Drawable drawable = context.getResources().getDrawable(resId);
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bitMapData = stream.toByteArray();

            cv.put(CharaContract.CharaEntry.COL_FILE,bitMapData);

            sqLiteDatabase.insert(CharaContract.CharaEntry.TABLE_NAME,null,cv);
        }

        Cursor cursor = sqLiteDatabase.rawQuery(CharaContract.CharaSql.SQL_QUERY_ALL_ROWS, null);
        Log.i("Norman","Table Filled. Rows = " + cursor.getCount());



    }
    private String convertJsonToString(int resource) {

        String line;
        String output = "";

        InputStream inputStream = getResources().openRawResource(resource);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            while ((line = reader.readLine()) != null) {
                output = output + line;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return output;
    }



    static class CharaData{

        private String title;
        private String country;

        public CharaData(String title, String country) {
            this.title = title;
            this.country = country;
        }


        public String getCountry() {
            return country;
        }


        public String getTitle() {
            return title;
        }
    }
}
