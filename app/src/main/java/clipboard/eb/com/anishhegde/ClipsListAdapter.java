package clipboard.anishhegde.com.clipboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anishhegde on 16/07/16.
 */
public class ClipsListAdapter extends ArrayAdapter<Clip> {

    Context mContext;
    List<Clip> mDataList;
    LayoutInflater mInflater;
    int mLayoutResourceId;
    public ClipsListAdapter(Context context, int resourceId, List<Clip> objects) {
        super(context,resourceId, objects);
        mContext = context;
        mDataList = objects;
        mLayoutResourceId = resourceId;
        mInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null){
            v = mInflater.inflate(mLayoutResourceId,null);
            holder = new ViewHolder();
            holder.clipText = (TextView) v.findViewById(R.id.text_clip_desc);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if(mDataList.size() > 0) {
            holder.clipText.setText(mDataList.get(position).getClip());
        } else {
            holder.clipText.setText("No Data");
        }
        return v;
    }

    public class ViewHolder{
        TextView clipText;
    }
}
