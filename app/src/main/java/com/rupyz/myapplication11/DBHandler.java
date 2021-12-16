package com.rupyz.myapplication11;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper{
    private static final String DB_NAME = "studentdb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "studentinfo";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String EMAIL_COL = "email";
    private static final String PHONE_COL = "phone";
    private static final String DOB_COL = "dob";
    private static final String GENDER_COL = "gender";
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
}
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PHONE_COL + " TEXT,"
                + DOB_COL + " TEXT,"
                + GENDER_COL + " TEXT)";
        db.execSQL(query);
    }
    public void addNewCourse(String name, String email, String phone, String dob, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, name);
        values.put(EMAIL_COL, email);
        values.put(PHONE_COL, phone);
        values.put(DOB_COL, dob);
        values.put(GENDER_COL,gender);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public ArrayList<PersonDetails> readCourses() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<PersonDetails> courseModalArrayList = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(new PersonDetails(cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getString(5)));
            } while (cursorCourses.moveToNext());
            }
        cursorCourses.close();
        return courseModalArrayList;
    }
    public void updateCourse(String originalName, String name, String email,
                             String phone, String dob, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, name);
        values.put(EMAIL_COL, email);
        values.put(PHONE_COL, phone);
        values.put(DOB_COL, dob);
        values.put(GENDER_COL,gender);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalName});
        db.close();
    }
    public void deleteCourse(String courseName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "name=?", new String[]{courseName});
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
