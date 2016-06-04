package apidez.com.samgu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import apidez.com.samgu.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nongdenchet on 6/4/16.
 */
public class BrowseMusicActivity extends AppCompatActivity {
    private boolean isSearchOpened = false;
    private MenuItem mSearchAction;
    private EditText edtSearch;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public static Intent getIntent(Context context) {
        return new Intent(context, BrowseMusicActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_music);
        ButterKnife.bind(this);
        initializeView();
    }

    private void initializeView() {
        setUpToolBar();
    }

    private void setUpToolBar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_browse_music, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                handleMenuSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void handleMenuSearch() {
        ActionBar action = getSupportActionBar();
        action.setDisplayHomeAsUpEnabled(isSearchOpened);
        action.setDisplayShowCustomEnabled(!isSearchOpened);
        action.setDisplayShowTitleEnabled(isSearchOpened);
        if (!isSearchOpened) {
            setUpCustomSearchBar();
        }
        handleKeyBoard();
        mSearchAction.setIcon(ContextCompat.getDrawable(this, menuItemIcon()));
        isSearchOpened = !isSearchOpened;
    }

    private void handleKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isSearchOpened) {
            imm.hideSoftInputFromWindow(edtSearch.getWindowToken(), 0);
        } else {
            imm.showSoftInput(edtSearch, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private int menuItemIcon() {
        return isSearchOpened ? R.drawable.ic_search_white_24dp : R.drawable.ic_close_white_24dp;
    }

    private void setUpCustomSearchBar() {
        ActionBar action = getSupportActionBar();
        View searchBar = LayoutInflater
                .from(action.getThemedContext())
                .inflate(R.layout.search_bar, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);
        action.setCustomView(searchBar, params);
        edtSearch = (EditText) action.getCustomView().findViewById(R.id.edtSearch);
        edtSearch.requestFocus();
    }
}
