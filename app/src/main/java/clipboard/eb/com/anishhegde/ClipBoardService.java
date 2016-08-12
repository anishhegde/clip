package clipboard.anishhegde.com.clipboard;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by anishhegde on 09/07/16.
 */
public class ClipBoardService extends Service {

    private ClipboardManager mClipBoardManager;
    IBinder mBinder;
    int mStartMode;
    String currentClip = "";
    ClipDataSource mDataSource;

    @Override
    public void onCreate() {
        mClipBoardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        mDataSource = new ClipDataSource(this);
        mDataSource.open();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mClipBoardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {

            @Override
            public void onPrimaryClipChanged() {
                if(mClipBoardManager.hasPrimaryClip()) {
                    String newClip = mClipBoardManager.getText().toString();
                    if(!newClip.equals(currentClip)) {
                        currentClip = newClip;
                        Toast.makeText(getApplicationContext(), newClip.toString(), Toast.LENGTH_LONG).show();
                        Clip clip = duplicateClip(newClip.toString());
                        if(clip != null){
                            mDataSource.deleteClip(clip);
                        }
                        mDataSource.createClip(newClip.toString());
                        Log.i("LOG_anish", newClip.toString() + "");
                    }
                }

            }
        });
        return mStartMode;
    }

    private Clip duplicateClip(String clipText){
        Clip clip = null;
        List<Clip> clipList = mDataSource.getAllClips();
        for(int i = 0; i < clipList.size(); i++){
            if(clipList.get(i).getClip().equals(clipText)){
                clip = clipList.get(i);
                break;
            }
        }
        return clip;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mDataSource.close();
        return super.onUnbind(intent);

    }

    @Override
    public void onDestroy() {
        mDataSource.close();
        super.onDestroy();
    }
}
