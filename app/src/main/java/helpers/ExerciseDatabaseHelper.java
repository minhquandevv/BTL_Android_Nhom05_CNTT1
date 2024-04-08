package helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import untity.Exercise;

public class ExerciseDatabaseHelper extends SQLiteOpenHelper {
    //Thư mục chứa database là assets
    private static final String DATABASE_NAME = "homeworkout.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_EXERCISE = "exercise";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_CALORIE_BURN = "calorie_burn";

    private static final String SQL_CREATE_TABLE_EXERCISE =
            "CREATE TABLE " + TABLE_EXERCISE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_DURATION + " INTEGER," +
                    COLUMN_CALORIE_BURN + " REAL)";

    public ExerciseDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_EXERCISE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
        onCreate(db);
    }

    public long addExercise(Exercise exercise) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, exercise.getName());
        values.put(COLUMN_DURATION, exercise.getDuration());
        values.put(COLUMN_CALORIE_BURN, exercise.getCaloBurn());
        return db.insert(TABLE_EXERCISE, null, values);
    }


    public Cursor getAllExercises() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_EXERCISE, null, null, null, null, null, null);
    }


    public Cursor searchExercise(String name) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_EXERCISE, null, COLUMN_NAME + " LIKE ?", new String[]{"%" + name + "%"}, null, null, null);
    }


    public long deleteExercise(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_EXERCISE, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }



}
