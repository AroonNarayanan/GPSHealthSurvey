package com.gpshealthsurvey.gpshealthsurvey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Louis on 3/24/2015.
 */
public class AttributeDataSource {

    private SQLiteDatabase database;
    private HealthSurveyDbHelper dbHelper;
    private String[] allColumns = { HealthSurveyDbHelper.ATTRIBUTE_COLUMN_ID, HealthSurveyDbHelper.ATTRIBUTE_COLUMN_NAME,
            HealthSurveyDbHelper.ATTRIBUTE_COLUMN_TYPE, HealthSurveyDbHelper.ATTRIBUTE_COLUMN_VALUE,
            HealthSurveyDbHelper.ATTRIBUTE_COLUMN_SURVEYID, HealthSurveyDbHelper.ATTRIBUTE_COLUMN_HOUSEHOLDID };

    public AttributeDataSource(Context context) {
        dbHelper = new HealthSurveyDbHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    public Attribute createAttribute(Attribute newAtt) {
        ContentValues values = new ContentValues();
        values.put(HealthSurveyDbHelper.ATTRIBUTE_COLUMN_NAME, newAtt.getName());
        values.put(HealthSurveyDbHelper.ATTRIBUTE_COLUMN_TYPE, newAtt.getType());
        values.put(HealthSurveyDbHelper.ATTRIBUTE_COLUMN_VALUE, newAtt.getValue());
        values.put(HealthSurveyDbHelper.ATTRIBUTE_COLUMN_SURVEYID, newAtt.getSurveyId());
        values.put(HealthSurveyDbHelper.ATTRIBUTE_COLUMN_HOUSEHOLDID, newAtt.getHouseholdId());
        long insertId = database.insert(HealthSurveyDbHelper.ATTRIBUTE_TABLE_NAME, null, values);
        Cursor cursor = database.query(HealthSurveyDbHelper.ATTRIBUTE_TABLE_NAME, allColumns, HealthSurveyDbHelper.ATTRIBUTE_COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Attribute newAttribute = cursorToAttribute(cursor);
        cursor.close();
        return newAttribute;
    }

    private Attribute cursorToAttribute(Cursor cursor) {
        Attribute attribute = new Attribute();
        attribute.setId(cursor.getLong(0));
        attribute.setName(cursor.getString(1));
        attribute.setType(cursor.getString(2));
        attribute.setValue(cursor.getString(3));
        attribute.setSurveyId(cursor.getInt(4));
        attribute.setHouseholdId(cursor.getInt(5));
        return attribute;
    }

    public void deleteAttribute(Attribute attribute) {
        long id = attribute.getId();
        System.out.println("Attribute deleted with id: " + id);
        database.delete(HealthSurveyDbHelper.ATTRIBUTE_TABLE_NAME, HealthSurveyDbHelper.ATTRIBUTE_COLUMN_ID
                + " = " + id, null);
    }

}
