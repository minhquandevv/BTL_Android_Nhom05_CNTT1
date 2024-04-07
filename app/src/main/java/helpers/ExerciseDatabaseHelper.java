package helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ExerciseDatabaseHelper extends SQLiteOpenHelper {
    // Database tôi đã có sẵn tên là homeworkout, không cần tạo mới nhưng cần tạo bảng mới

    private static final String TABLE_EXERCISES = "exercises";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_VIDEO = "video";
    private static final String COLUMN_CREATE_TIME = "create_time";

    private static final String CREATE_TABLE_EXERCISES = "CREATE TABLE " + TABLE_EXERCISES + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_IMAGE + " TEXT,"
            + COLUMN_VIDEO + " TEXT,"
            + COLUMN_CREATE_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    public ExerciseDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "homeworkout", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EXERCISES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        onCreate(db);
    }

    // Tạo các phương thức CRUD cho bảng exercises

    // Thêm một bài tập mới
    public void addExercise(String name, String description, String image, String video) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_IMAGE, image);
        values.put(COLUMN_VIDEO, video);

        db.insert(TABLE_EXERCISES, null, values);
        db.close();
    }

    // Xóa một bài tập
    public void deleteExercise(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXERCISES, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Lấy tất cả bài tập
    public Cursor getAllExercises() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_EXERCISES, null);
    }



}
