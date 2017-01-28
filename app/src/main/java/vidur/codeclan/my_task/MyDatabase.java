package vidur.codeclan.my_task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabase {

    // Constants: Database name, version, column, queries

    public static final String DB_NAME = "task.db";
    public static final int DB_VER = 2;
    public static final String DB_TABLE = "Tasks";
    public static final String C_TaskName = "TaskName";
    public static final String C_TaskDetails = "TaskDetails";

    // queries

    public static final String Q_Create = "CREATE TABLE " + DB_TABLE +
            "(" + C_TaskName + " text, " + C_TaskDetails + " text)";

    Context context;
    private SQLiteDatabase database;

    public MyDatabase(Context c) {
        context = c;
    }

    public MyDatabase open() {
        DBHelper dbhelper = new DBHelper(context);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void save(String t_name, String t_details) {
        ContentValues cv = new ContentValues();
        cv.put(C_TaskName, t_name);
        cv.put(C_TaskDetails, t_details);
        database.insert(DB_TABLE, null, cv);
    }

    public void close() {
        database.close();
    }

    public String read() {

        String result = "";

        String[] columns = {C_TaskName, C_TaskDetails};
        Cursor cursor  =
                database.query(DB_TABLE, columns, null,
                        null, null, null, null);

        int iTName = cursor.getColumnIndex(C_TaskName);
        int iTDetails = cursor.getColumnIndex(C_TaskDetails);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            result = result + cursor.getString(iTName) + " - " +
                    cursor.getString(iTDetails) + "\n";
        }

        return result;
    }

    private class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VER);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Execute create query here
            db.execSQL(Q_Create);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Execute upgrade query here
        }
    }

}
