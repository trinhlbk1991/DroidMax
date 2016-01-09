package com.icetea09.droidmax.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "com.icetea09.droidmax.database";

    public static final String TABLE_RULES = "Rules";

    public static final String KEY_ID = "ItemId";
    public static final String KEY_NAME = "Name";
    public static final String KEY_CATEGORIES = "Categories";
    public static final String KEY_CONDITIONS = "Conditions";
    public static final String KEY_ACTIONS = "Actions";
    public static final String KEY_NUM_OF_PERFORMED = "NumOfPerformed";


    private final String SQL_CREATE_TABLE_RULES = "CREATE TABLE IF NOT EXISTS "
            + TABLE_RULES + "(" + KEY_ID + " TEXT," + KEY_NAME + " TEXT,"
            + KEY_CATEGORIES + " TEXT, " + KEY_CONDITIONS + " TEXT, "
            + KEY_ACTIONS + " TEXT, " + KEY_NUM_OF_PERFORMED + " INTEGER, "
            + "PRIMARY KEY ('" + KEY_ID + "'))";


    private final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS ";

    private static SQLiteHelper mInstance;

    private SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized SQLiteHelper getInstance(Context context) {
        if (mInstance == null)
            mInstance = new SQLiteHelper(context);
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_RULES);
        Log.d(TAG, "Created database.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTable(TABLE_RULES, db);
        onCreate(db);
    }

    private void dropTable(String tableName, SQLiteDatabase db) {
        db.execSQL(SQL_DROP_TABLE + tableName);
    }

}
