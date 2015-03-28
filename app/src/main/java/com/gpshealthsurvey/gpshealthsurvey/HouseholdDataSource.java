package com.gpshealthsurvey.gpshealthsurvey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Louis on 3/27/2015.
 */
public class HouseholdDataSource {

    private SQLiteDatabase database;
    private HealthSurveyDbHelper dbHelper;
    private String[] allColumns = { HealthSurveyDbHelper.HOUSEHOLD_COLUMN_HOUSEID, HealthSurveyDbHelper.HOUSEHOLD_COLUMN_DESCRIPTION,
            HealthSurveyDbHelper.HOUSEHOLD_COLUMN_LATITUDE, HealthSurveyDbHelper.HOUSEHOLD_COLUMN_LONGITUDE };

    public HouseholdDataSource(Context context) {
        dbHelper = new HealthSurveyDbHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    public Household createHousehold(Household newHouse) {
        ContentValues values = new ContentValues();
        values.put(HealthSurveyDbHelper.HOUSEHOLD_COLUMN_HOUSEID, newHouse.getHouseId());
        values.put(HealthSurveyDbHelper.HOUSEHOLD_COLUMN_DESCRIPTION, newHouse.getDescription());
        values.put(HealthSurveyDbHelper.HOUSEHOLD_COLUMN_LATITUDE, newHouse.getLatitude());
        values.put(HealthSurveyDbHelper.HOUSEHOLD_COLUMN_LONGITUDE, newHouse.getLongitude());
        long insertId = database.insert(HealthSurveyDbHelper.HOUSEHOLD_TABLE_NAME, null, values);
        Cursor cursor = database.query(HealthSurveyDbHelper.HOUSEHOLD_TABLE_NAME, allColumns, HealthSurveyDbHelper.HOUSEHOLD_COLUMN_HOUSEID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Household newHousehold = cursorToHousehold(cursor);
        cursor.close();
        return newHousehold;
    }

    private Household cursorToHousehold(Cursor cursor) {
        Household household = new Household();
        household.setHouseId(cursor.getLong(0));
        household.setDescription(cursor.getString(1));
        household.setLatitude(cursor.getDouble(2));
        household.setLongitude(cursor.getDouble(3));
        return household;
    }

    public void deleteHousehold(Household household) {
        long id = household.getHouseId();
        System.out.println("Household deleted with HouseId: " + id);
        database.delete(HealthSurveyDbHelper.HOUSEHOLD_TABLE_NAME, HealthSurveyDbHelper.HOUSEHOLD_COLUMN_HOUSEID
                + " = " + id, null);
    }
}
