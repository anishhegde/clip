package clipboard.anishhegde.com.clipboard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by anishhegde on 16/07/16.
 */
public class ClipSQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_CLIPS = "clips";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CLIP = "clip";
    public static final String COLUMN_TIME_STAMP = "time_stamp";

    private static final String DATABASE_NAME = "clips.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_CLIPS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_CLIP
            + " text not null);";


    public ClipSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Log.w(ClipSQLiteHelper.class.getName(),
//                "Upgrading database from version " + oldVersion + " to "
//                        + newVersion + ", which will destroy all old data");
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIPS);
//        onCreate(db);
    }
}
