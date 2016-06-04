package apidez.com.samgu.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import apidez.com.samgu.R;
import apidez.com.samgu.adapter.SearchResultAdapter;
import apidez.com.samgu.databinding.ActivityBrowseMusicBinding;
import apidez.com.samgu.viewmodel.SearchViewModel;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by nongdenchet on 6/4/16.
 */
public class BrowseMusicActivity extends AppCompatActivity {
    private boolean isSearchOpened = false;
    private MenuItem mSearchAction;
    private EditText edtSearch;
    private ActivityBrowseMusicBinding binding;
    private SearchViewModel searchViewModel;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.rvSearchResult)
    RecyclerView rvSearchResult;

    public static Intent getIntent(Context context) {
        return new Intent(context, BrowseMusicActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_browse_music);
        ButterKnife.bind(this);
        searchViewModel = new SearchViewModel(
                AndroidSchedulers.mainThread(),
                Schedulers.computation());
        binding.setViewModel(searchViewModel);
        initializeView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchViewModel.bindSearchResult()
                .subscribe(result -> {
                }, Throwable::printStackTrace);
    }

    private void initializeView() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvSearchResult.setAdapter(new SearchResultAdapter());
        rvSearchResult.setLayoutManager(new LinearLayoutManager(this));
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
        searchViewModel.onKeyWordChange("");
        handleKeyBoard();
        mSearchAction.setIcon(ContextCompat.getDrawable(this, menuItemIcon()));
        isSearchOpened = !isSearchOpened;
    }

    private void handleKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isSearchOpened) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
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
        setUpSearhBar(searchBar);
    }

    private void setUpSearhBar(View searchBar) {
        edtSearch = (EditText) searchBar.findViewById(R.id.edtSearch);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchViewModel.onKeyWordChange(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        edtSearch.requestFocus();
    }
}
