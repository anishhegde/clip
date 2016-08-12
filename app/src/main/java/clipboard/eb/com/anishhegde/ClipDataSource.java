package clipboard.anishhegde.com.clipboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anishhegde on 16/07/16.
 */
public class ClipDataSource {

    // Database fields
    private SQLiteDatabase database;
    private ClipSQLiteHelper dbHelper;
    private String[] allColumns = { ClipSQLiteHelper.COLUMN_ID,
            ClipSQLiteHelper.COLUMN_CLIP };

    public ClipDataSource(Context context) {
        dbHelper = new ClipSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Clip createClip(String clip) {
        ContentValues values = new ContentValues();
        values.put(ClipSQLiteHelper.COLUMN_CLIP, clip);
        long insertId = database.insert(ClipSQLiteHelper.TABLE_CLIPS, null,
                values);
        Cursor cursor = database.query(ClipSQLiteHelper.TABLE_CLIPS,
                allColumns, ClipSQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Clip newClip = cursorToComment(cursor);
        cursor.close();
        return newClip;
    }

    public void deleteClip(Clip clip) {
        long id = clip.getId();
        System.out.println("Clip deleted with id: " + id);
        database.delete(ClipSQLiteHelper.TABLE_CLIPS, ClipSQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Clip> getAllClips() {
        List<Clip> clips = new ArrayList<Clip>();

        Cursor cursor = database.query(ClipSQLiteHelper.TABLE_CLIPS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Clip clip = cursorToComment(cursor);
            clips.add(clip);
            cursor.moveToNext();
        }
        cursor.close();
        return clips;
    }

    private Clip cursorToComment(Cursor cursor) {
        Clip clip = new Clip();
        clip.setId(cursor.getLong(0));
        clip.setClip(cursor.getString(1));
        return clip;
    }
}
