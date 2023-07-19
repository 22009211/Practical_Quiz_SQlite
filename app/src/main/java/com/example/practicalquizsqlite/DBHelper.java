package com.example.practicalquizsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "schools.db";
    private static final String TABLE_SCHOOL = "school";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NUM_OF_STU = "num_of_stu";
    private static final String COLUMN_SCHOOL_NAME = "school_name";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //ToDo
        String createNoteTableSql = "CREATE TABLE " + TABLE_SCHOOL + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NUM_OF_STU + " TEXT,"
                + COLUMN_SCHOOL_NAME + " TEXT ) ";
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

        //Dummy records, to be inserted when the database is created
        for (int i = 0; i< 2; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NUM_OF_STU, "Number of students " + i);
            values.put(COLUMN_SCHOOL_NAME, "School name " + i);
            db.insert(TABLE_SCHOOL, null, values);
        }
        Log.i("info", "dummy records inserted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHOOL);
        onCreate(db);
    }

    public long insertSchool(String numOfStudent, String schoolName){
        //ToDo
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues stuNum = new ContentValues();
        ContentValues schName = new ContentValues();
        stuNum.put(COLUMN_NUM_OF_STU, numOfStudent);
        schName.put(COLUMN_SCHOOL_NAME, schoolName);
        long result = db.insert(TABLE_SCHOOL, schoolName, stuNum);
        db.close();
        Log.d("SQL Insert", numOfStudent + ": " + schoolName); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<School> getSchools() {
        //ToDO
        ArrayList<School> notes = new ArrayList<School>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_NUM_OF_STU, COLUMN_SCHOOL_NAME};

        Cursor cursor = db.query(TABLE_SCHOOL, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int numOfStudent = cursor.getInt(1);
                String schoolName = cursor.getString(2);
                School school = new School(id, numOfStudent, schoolName); //create object
                notes.add(school);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
        }
    }