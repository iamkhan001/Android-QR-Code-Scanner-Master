package studio.n.qrscanner.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import studio.n.qrscanner.LogModel;

/**
 * Created by Imran Khan on 31 may 2018.
 */

public class DataProcessor {

    private Context context;
    private AppDataBase dataBase;

    public DataProcessor(Context context) {
        this.context = context;
    }


    public long insertLog(String time, String date, String description) {
        dataBase = new AppDataBase(context);

        SQLiteDatabase dbb = dataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.LOG_TIME, time);
        contentValues.put(Config.LOG_DATE, date);
        contentValues.put(Config.LOG_TITLE, description);

        long l = dbb.insert(Config.TABLE_LOGS, null, contentValues);
        Log.e("DATA", "INSERTED " + l);
        return l;
    }

    public ArrayList<LogModel> getLogs() {
        ArrayList<LogModel> logs = new ArrayList<>();
        dataBase = new AppDataBase(context);
        SQLiteDatabase db = dataBase.getReadableDatabase();
        String[] columns = {Config.LOG_ID, Config.LOG_TIME, Config.LOG_DATE, Config.LOG_TITLE};
        Cursor cursor = db.query(Config.TABLE_LOGS, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            logs.add(new LogModel(
                    cursor.getString(cursor.getColumnIndex(Config.LOG_ID)),
                    cursor.getString(cursor.getColumnIndex(Config.LOG_TIME)),
                    cursor.getString(cursor.getColumnIndex(Config.LOG_DATE)),
                    cursor.getString(cursor.getColumnIndex(Config.LOG_TITLE))
            ));
            Log.e("DATA", " > " + cursor.getString(cursor.getColumnIndex(Config.LOG_ID)));
        }
        cursor.close();
        return logs;
    }


    public void clearLogs() {
        dataBase = new AppDataBase(context);
        SQLiteDatabase db = dataBase.getReadableDatabase();
        db.execSQL("delete from " + Config.TABLE_LOGS);
    }


}
