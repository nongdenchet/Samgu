package apidez.com.samgu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import apidez.com.samgu.R;
import apidez.com.samgu.adapter.FriendListAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nongdenchet on 6/5/16.
 */
public class FriendListFragment extends Fragment {

    @Bind(R.id.rvFriendList)
    RecyclerView rvFriendList;

    public static FriendListFragment newInstance() {
        Bundle args = new Bundle();
        FriendListFragment fragment = new FriendListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friend_list, container, false);
        ButterKnife.bind(this, rootView);
        rvFriendList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFriendList.setAdapter(new FriendListAdapter());
        return rootView;
    }
}
