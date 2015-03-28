package com.gpshealthsurvey.gpshealthsurvey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Louis on 3/27/2015.
 */
public class ProjectDataSource {

    private SQLiteDatabase database;
    private HealthSurveyDbHelper dbHelper;
    private String[] allColumns = { HealthSurveyDbHelper.PROJECT_COLUMN_PROJECTID, HealthSurveyDbHelper.PROJECT_COLUMN_NAME,
            HealthSurveyDbHelper.PROJECT_COLUMN_DESCRIPTION, HealthSurveyDbHelper.PROJECT_COLUMN_LATITUDE,
            HealthSurveyDbHelper.PROJECT_COLUMN_LONGITUDE };

    public ProjectDataSource(Context context) {
        dbHelper = new HealthSurveyDbHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    public Project createProject(Project project) {
        ContentValues values = new ContentValues();
        values.put(HealthSurveyDbHelper.PROJECT_COLUMN_PROJECTID, project.getProjectId());
        values.put(HealthSurveyDbHelper.PROJECT_COLUMN_NAME, project.getName());
        values.put(HealthSurveyDbHelper.PROJECT_COLUMN_DESCRIPTION, project.getDescription());
        values.put(HealthSurveyDbHelper.PROJECT_COLUMN_LATITUDE, project.getLatitude());
        values.put(HealthSurveyDbHelper.PROJECT_COLUMN_LONGITUDE, project.getLongitude());
        long insertId = database.insert(HealthSurveyDbHelper.PROJECT_TABLE_NAME, null, values);
        Cursor cursor = database.query(HealthSurveyDbHelper.PROJECT_TABLE_NAME, allColumns, HealthSurveyDbHelper.PROJECT_COLUMN_PROJECTID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Project newProject = cursorToProject(cursor);
        cursor.close();
        return newProject;
    }

    private Project cursorToProject(Cursor cursor) {
        Project project = new Project();
        project.setProjectId(cursor.getLong(0));
        project.setName(cursor.getString(1));
        project.setDescription(cursor.getString(2));
        project.setLatitude(cursor.getDouble(3));
        project.setLongitude(cursor.getDouble(4));
        return project;
    }

    public void deleteHousehold(Project household) {
        long id = household.getProjectId();
        System.out.println("Household deleted with HouseId: " + id);
        database.delete(HealthSurveyDbHelper.PROJECT_TABLE_NAME, HealthSurveyDbHelper.PROJECT_COLUMN_PROJECTID
                + " = " + id, null);
    }
}
