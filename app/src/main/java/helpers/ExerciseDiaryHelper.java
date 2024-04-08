package helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import untity.Exercise;

public class ExerciseDiaryHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "homeworkout.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_EXERCISE = "diary";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_CALORIE_BURN = "calorie_burn";

    public ExerciseDiaryHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public int getExerciseCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_EXERCISE, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    public double getCaloBurnSum() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_CALORIE_BURN + ") FROM " + TABLE_EXERCISE, null);
        cursor.moveToFirst();
        double sum = cursor.getDouble(0);
        cursor.close();
        return sum;
    }

    public int getDurationSum() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_DURATION + ") FROM " + TABLE_EXERCISE, null);
        cursor.moveToFirst();
        int sum = cursor.getInt(0);
        cursor.close();
        return sum;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_EXERCISE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DURATION + " INTEGER, " +
                COLUMN_CALORIE_BURN + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
        onCreate(db);
    }

    public void addExerciseDiary(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, exercise.getName());
        values.put(COLUMN_DURATION, exercise.getDuration());
        values.put(COLUMN_CALORIE_BURN, exercise.getCaloBurn());
        db.insert(TABLE_EXERCISE, null, values);
        db.close();
    }

    public Cursor getAllExercises() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_EXERCISE, null, null, null, null, null, null);
    }

    public void deleteExercise(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXERCISE, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
