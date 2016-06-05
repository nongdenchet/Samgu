package apidez.com.samgu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import apidez.com.samgu.R;
import apidez.com.samgu.activity.BrowseMusicActivity;
import apidez.com.samgu.activity.SamguActivity;
import apidez.com.samgu.adapter.SongListAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nongdenchet on 6/5/16.
 */
public class SongListFragment extends Fragment {

    @Bind(R.id.rvSongList)
    RecyclerView rvSongList;

    @OnClick(R.id.tvListening)
    public void onListeningClick() {
        EventBus.getDefault().post(new SamguActivity.TabEvent(SamguActivity.SAMGU_TAB_INDEX));
    }

    @OnClick(R.id.fab)
    public void onSearchBtnClick() {
        startActivity(BrowseMusicActivity.getIntent(getContext(), BrowseMusicActivity.CHOOSE_MUSIC));
    }

    public static SongListFragment newInstance() {
        Bundle args = new Bundle();
        SongListFragment fragment = new SongListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_song_list, container, false);
        ButterKnife.bind(this, rootView);
        setUpRecyclerView();
        return rootView;
    }

    private void setUpRecyclerView() {
        rvSongList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSongList.setAdapter(new SongListAdapter());
    }
}
