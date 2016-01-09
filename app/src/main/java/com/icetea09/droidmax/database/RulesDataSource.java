package com.icetea09.droidmax.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.icetea09.droidmax.model.Rule;

import java.util.ArrayList;
import java.util.List;

public class RulesDataSource {

    private static final String TAG = RulesDataSource.class.getSimpleName();

    private SQLiteHelper mSQLiteHelper;

    public RulesDataSource(Context context) {
        mSQLiteHelper = SQLiteHelper.getInstance(context);
    }

    public boolean addNewRule(Rule item) {
        try {
            SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(SQLiteHelper.KEY_ID, item.getId());
            values.put(SQLiteHelper.KEY_NAME, item.getName());
            values.put(SQLiteHelper.KEY_CATEGORIES, item.getCategoriesString());
            values.put(SQLiteHelper.KEY_CONDITIONS, item.getConditionsString());
            values.put(SQLiteHelper.KEY_ACTIONS, item.getActionsString());
            values.put(SQLiteHelper.KEY_NUM_OF_PERFORMED, item.getNumOfPerformed());
            db.insert(SQLiteHelper.TABLE_RULES, null, values);
            Log.i(TAG, "Added successfully: " + item.getName());
            return true;

        } catch (Exception e) {
            Log.e(TAG, "Added failed: " + item.getName());
            e.printStackTrace();
            return false;
        }
    }

    public List<Rule> getRulesByCategory(String category) {
        List<Rule> result = new ArrayList<>();

        try {
            SQLiteDatabase db = mSQLiteHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_RULES + " WHERE "
                    + SQLiteHelper.KEY_CATEGORIES + " LIKE '%" + category + "%' ", null);
            if (cursor.moveToFirst()) {
                do {
                    Rule rule = new Rule();
                    rule.setId(cursor.getString(0));
                    rule.setName(cursor.getString(1));
                    rule.setCategories(cursor.getString(2));
                    rule.setConditions(cursor.getString(3));
                    rule.setActions(cursor.getString(4));
                    rule.setNumOfPerformed(cursor.getInt(5));
                    result.add(rule);
                } while (cursor.moveToNext());
                cursor.close();
                db.close();
            }

        } catch (Exception e) {
            Log.e(TAG, "Failed to get rules by category: " + category);
            e.printStackTrace();
        }

        return result;
    }

    public Rule getRuleById(String id) {
        Rule rule = null;
        try {
            SQLiteDatabase db = mSQLiteHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_RULES + " WHERE "
                    + SQLiteHelper.KEY_ID + "='" + id + "'", null);
            if (cursor.moveToFirst()) {
                rule = new Rule();
                rule.setId(cursor.getString(0));
                rule.setName(cursor.getString(1));
                rule.setCategories(cursor.getString(2));
                rule.setConditions(cursor.getString(3));
                rule.setActions(cursor.getString(4));
                rule.setNumOfPerformed(cursor.getInt(5));
                cursor.close();
                db.close();
            }

        } catch (Exception e) {
            Log.e(TAG, "Failed to get rules by id: " + id);
            e.printStackTrace();
        }
        return rule;
    }

    public boolean updateRule(Rule item) {
        try {
            SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(SQLiteHelper.KEY_ID, item.getId());
            values.put(SQLiteHelper.KEY_NAME, item.getName());
            values.put(SQLiteHelper.KEY_CATEGORIES, item.getCategoriesString());
            values.put(SQLiteHelper.KEY_CONDITIONS, item.getConditionsString());
            values.put(SQLiteHelper.KEY_ACTIONS, item.getActionsString());
            values.put(SQLiteHelper.KEY_NUM_OF_PERFORMED, item.getNumOfPerformed());
            db.update(SQLiteHelper.TABLE_RULES, values, SQLiteHelper.KEY_ID + "='" + item.getId() + "'", null);
            Log.i(TAG, "Updated successfully: " + item.getName());
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Updated failed: " + item.getName());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRule(Rule item) {
        try {
            SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
            db.execSQL("DELETE FROM " + SQLiteHelper.TABLE_RULES + " WHERE " + SQLiteHelper.KEY_ID + "='" + item.getId() + "'");
            Log.i(TAG, "Deleted successfully: " + item.getName());
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Deleted failed: " + item.getName());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllRules() {
        try {
            SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
            db.delete(SQLiteHelper.TABLE_RULES, null, null);
            Log.i(TAG, "Deleted all successfully");
            return true;
        } catch (Exception e) {
            Log.i(TAG, "Deleted all failed");
            e.printStackTrace();
            return false;
        }

    }

}
