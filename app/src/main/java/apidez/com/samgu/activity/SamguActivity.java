package apidez.com.samgu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import apidez.com.samgu.R;
import apidez.com.samgu.fragment.FriendListFragment;
import apidez.com.samgu.fragment.SamguFragment;
import apidez.com.samgu.fragment.SongListFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SamguActivity extends AppCompatActivity {
    public static int SAMGU_TAB_INDEX = 0;

    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Bind(R.id.container)
    ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.ic_samgu,
            R.drawable.ic_chat,
            R.drawable.ic_musicnote
    };

    public static Intent getIntent(Context context) {
        return new Intent(context, SamguActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samgu);
        ButterKnife.bind(this);
        setUpViewPager();
        setUpTabLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    private void setUpTabLayout() {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setCustomView(LayoutInflater.from(this).inflate(R.layout.custom_tab, null));
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(2).select();
    }

    private void setUpViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(SamguFragment.newInstance());
        adapter.addFragment(FriendListFragment.newInstance());
        adapter.addFragment(SongListFragment.newInstance());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

    @Subscribe
    public void onEvent(TabEvent event) {
        tabLayout.getTabAt(event.tabIndex).select();
    }

    public static class TabEvent {
        public int tabIndex;

        public TabEvent(int tabIndex) {
            this.tabIndex = tabIndex;
        }
    }
}
