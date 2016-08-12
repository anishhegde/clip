package clipboard.anishhegde.com.clipboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ClipListingActivity extends AppCompatActivity {

    protected ListView mClipsListView;
    protected ClipsListAdapter mAdapter;
    protected ClipDataSource mDataSource;
    protected List<Clip> mClipsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_listing);
        startService(new Intent(this, ClipBoardService.class));

        mDataSource = new ClipDataSource(this);
        mDataSource.open();
        setUpList();
    }

    @Override
    protected void onResume() {
        mDataSource.open();
        refreshList();
        super.onResume();
    }

    private void setUpList(){
        mClipsList = new ArrayList<Clip>();
        mClipsListView = (ListView) findViewById(R.id.list_clips);
        mAdapter = new ClipsListAdapter(this,R.layout.item_clip,mClipsList);
        mClipsListView.setAdapter(mAdapter);
    }

    private void refreshList(){
        mAdapter.clear();
        List<Clip> refreshedList = mDataSource.getAllClips();
        Collections.reverse(refreshedList);
        mAdapter.addAll(refreshedList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        mDataSource.close();
        super.onPause();
    }

}
