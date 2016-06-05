package apidez.com.samgu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import apidez.com.samgu.R;
import butterknife.ButterKnife;

/**
 * Created by nongdenchet on 6/5/16.
 */
public class FriendListFragment extends Fragment {

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
        return rootView;
    }
}
