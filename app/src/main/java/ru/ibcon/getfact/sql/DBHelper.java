package ru.ibcon.getfact.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GetFact";

    public static final String TABLE_PROJECTS                       = "projects";
    public static final String TABLE_ACTIVITIES                     = "activities";
    public static final String TABLE_ACTIVITY_CODE                  = "activity_code";
    public static final String TABLE_ACTIVITY_CODE_TYPE             = "activity_code_type";
    public static final String TABLE_ACTIVITY_CODE_ASSIGNMENT       = "activity_code_assignment";

    public static final String TABLE_UDF_VALUE                      = "udf_value";
    public static final String TABLE_STEP                           = "step";
    public static final String TABLE_FACT                           = "facts";

    public static final String KEY_AID                              = "_id";

    public static final String KEY_ID                               = "id";
    public static final String KEY_OID                              = "object_id";
    public static final String KEY_POID                             = "project_object_id";
    public static final String KEY_PID                              = "parent_id";
    public static final String KEY_SHORT_NAME                       = "short_name";
    public static final String KEY_NAME                             = "name";
    public static final String KEY_CODE_TYPE_OBJECT_ID              = "code_type_object_id";

    public static final String KEY_DATE                             = "date";
    public static final String KEY_START_DATE                       = "start_date";
    public static final String KEY_FINISH_DATE                      = "finish_date";

    public static final String KEY_ACTIVITY_ID                     = "activity_id";
    public static final String KEY_ACTIVITY_CODE_ID                = "activity_code_id";

    public static final String KEY_WEIGHT                          = "weight";
    public static final String KEY_WEIGHT_PERCENT                  = "weight_percent";
    public static final String KEY_PERCENT_COMPLETE                = "percent_complete";

    public static final String KEY_PV                              = "pv";

    public static final String KEY_TYPE                            = "type";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create TABLE " + TABLE_PROJECTS + "("
                + KEY_OID                           + " integer primary key, "
                + KEY_SHORT_NAME                    + " text,"
                + KEY_NAME                          + " text,"
                + KEY_DATE                          + " integer"
                + ")");

        db.execSQL("create TABLE " + TABLE_ACTIVITIES + "("
                + KEY_OID                           + " integer, "
                + KEY_POID                          + " integer, "
                + KEY_PID                           + " integer, "
                + KEY_SHORT_NAME                    + " text,"
                + KEY_NAME                          + " text,"
                + KEY_START_DATE                    + " integer,"
                + KEY_FINISH_DATE                   + " integer"
                + ")");

        db.execSQL("create TABLE " + TABLE_ACTIVITY_CODE + "("
                + KEY_OID                           + " integer, "
                + KEY_PID                           + " integer, "
                + KEY_SHORT_NAME                    + " text,"
                + KEY_NAME                          + " text,"
                + KEY_CODE_TYPE_OBJECT_ID           + " integer"
                + ")");

        db.execSQL("create TABLE " + TABLE_ACTIVITY_CODE_TYPE + "("
                + KEY_OID                           + " integer, "
                + KEY_NAME                          + " text"
                + ")");

        db.execSQL("create TABLE " + TABLE_ACTIVITY_CODE_ASSIGNMENT + "("
                + KEY_ACTIVITY_ID                   + " integer, "
                + KEY_ACTIVITY_CODE_ID              + " integer"
                + ")");

        db.execSQL("create TABLE " + TABLE_STEP + "("
                + KEY_OID                           + " integer, "
                + KEY_ACTIVITY_ID                   + " integer, "
                + KEY_NAME                          + " text,"
                + KEY_WEIGHT                        + " real,"
                + KEY_WEIGHT_PERCENT                + " real,"
                + KEY_PERCENT_COMPLETE              + " real"
                + ")");

        db.execSQL("create TABLE " + TABLE_UDF_VALUE + "("
                + KEY_PID                           + " integer, "
                + KEY_PV                            + " real"
                + ")");

        db.execSQL("create TABLE " + TABLE_FACT + "("
                + KEY_OID                           + " integer, "
                + KEY_TYPE                          + " integer, "
                + KEY_PERCENT_COMPLETE              + " real"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVerdion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_PROJECTS);
        db.execSQL("drop table if exists " + TABLE_ACTIVITIES);
        db.execSQL("drop table if exists " + TABLE_ACTIVITY_CODE);
        db.execSQL("drop table if exists " + TABLE_ACTIVITY_CODE_TYPE);
        db.execSQL("drop table if exists " + TABLE_ACTIVITY_CODE_ASSIGNMENT);
        db.execSQL("drop table if exists " + TABLE_STEP);
        db.execSQL("drop table if exists " + TABLE_UDF_VALUE);
        db.execSQL("drop table if exists " + TABLE_FACT);

        onCreate(db);
    }
}
