package clipboard.anishhegde.com.clipboard;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by anishhegde on 10/07/16.
 */
public class ClipBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            ComponentName service = context.startService(
                    new Intent(context, ClipBoardService.class));
            if (service == null) {
                Log.e("TAG", "Can't start service");
            }
        } else {
            Log.e("TAG", "Recieved unexpected intent " + intent.toString());
        }
    }
}
