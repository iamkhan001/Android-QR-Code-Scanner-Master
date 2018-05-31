package studio.n.qrscanner.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *Created by Imran Khan on 31 may 2018.
 */

class AppDataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;


    private Context context;


    public AppDataBase(Context context) {
        super(context, Config.APP_DATABASE, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_TABLE_LOGS = "CREATE TABLE " + Config.TABLE_LOGS +
                " (" + Config.LOG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.LOG_TIME + " VARCHAR(10) ,"
                + Config.LOG_DATE + " VARCHAR(8) ,"
                + Config.LOG_TITLE + " VARCHAR(20));";

        try {
            sqLiteDatabase.execSQL(CREATE_TABLE_LOGS);
        } catch (Exception e) {
            Message.show(context, "" + e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String DROP_TABLE_LOGS = "DROP TABLE IF EXISTS " + Config.TABLE_LOGS;

        try {
            Message.show(context, "OnUpgrade");
            sqLiteDatabase.execSQL(DROP_TABLE_LOGS);
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            Message.show(context, "" + e);
        }

    }
}
