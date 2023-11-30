package com.example.nibm;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "NIBM.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "student_info";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_INDEX = " registration_number";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_MOBILE = "mobile_number";
    private static final String COLUMN_PARENT = "parent_number";



     MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                COLUMN_INDEX + " TEXT UNIQUE, " +
                                COLUMN_NAME + " TEXT, " +
                                COLUMN_AGE + " TEXT, " +
                                COLUMN_GENDER + " TEXT, " +
                                COLUMN_MOBILE + " TEXT, " +
                                COLUMN_PARENT + " TEXT); ";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    void addStudent (String index, String name, String age, String gender, String mobile, String home) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if the index already exists
        if (!isIndexUnique(index)) {
            Toast.makeText(context, "Index must be unique", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_INDEX, index);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AGE, age);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_MOBILE, mobile);
        cv.put(COLUMN_PARENT, home);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show();
            // Redirect to the main activity
            Intent intent = new Intent(context, AdminPanel.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
    }

    private boolean isIndexUnique(String index) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_INDEX}, COLUMN_INDEX + "=?",
                new String[]{index}, null, null, null);
        boolean isUnique = cursor.getCount() == 0;
        cursor.close();
        return isUnique;
    }


    Cursor readAllData (){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String index, String name, String age, String gender, String mobile, String home){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();
         cv.put(COLUMN_INDEX, index);
         cv.put(COLUMN_NAME, name);
         cv.put(COLUMN_AGE, age);
         cv.put(COLUMN_GENDER, gender);
         cv.put(COLUMN_MOBILE, mobile);
         cv.put(COLUMN_PARENT, home);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
         SQLiteDatabase db = this.getWritableDatabase();
         long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
         if(result == -1){
             Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
         }else{
             Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
         }
    }

    void deleteAllData(){
         SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
